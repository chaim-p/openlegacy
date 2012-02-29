package org.openlegacy.terminal.support.navigation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.ScreenPojoFieldAccessor;
import org.openlegacy.terminal.TerminalSession;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.FieldAssignDefinition;
import org.openlegacy.terminal.definitions.NavigationDefinition;
import org.openlegacy.terminal.definitions.ScreenEntityDefinition;
import org.openlegacy.terminal.exceptions.ScreenEntityNotAccessibleException;
import org.openlegacy.terminal.spi.ScreenEntitiesRegistry;
import org.openlegacy.terminal.spi.ScreensRecognizer;
import org.openlegacy.terminal.spi.SessionNavigator;
import org.openlegacy.terminal.utils.ScreenNavigationUtil;
import org.openlegacy.terminal.utils.SimpleScreenPojoFieldAccessor;
import org.openlegacy.utils.ProxyUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class DefaultSessionNavigator implements SessionNavigator {

	@Inject
	private ScreenEntitiesRegistry screenEntitiesRegistry;

	@Inject
	private ScreensRecognizer screensRecognizer;

	@Inject
	private NavigationMetadata navigationMetadata;

	private final static Log logger = LogFactory.getLog(DefaultSessionNavigator.class);

	public void navigate(TerminalSession terminalSession, Class<?> targetScreenEntity) throws ScreenEntityNotAccessibleException {

		TerminalSnapshot snapshot = terminalSession.getSnapshot();
		Class<?> currentEntityClass = screensRecognizer.match(snapshot);

		if (currentEntityClass == null) {
			return;
		}

		if (ProxyUtil.isClassesMatch(currentEntityClass, targetScreenEntity)) {
			return;
		}

		ScreenEntityDefinition currentEntityDefinition = screenEntitiesRegistry.get(currentEntityClass);
		ScreenEntityDefinition targetEntityDefinition = screenEntitiesRegistry.get(targetScreenEntity);

		List<NavigationDefinition> navigationSteps = navigationMetadata.get(currentEntityDefinition, targetEntityDefinition);

		while (navigationSteps == null) {
			navigationSteps = findDirectNavigationPath(currentEntityDefinition, targetEntityDefinition);

			if (navigationSteps == null) {
				NavigationDefinition currentScreenNavigationDefinition = currentEntityDefinition.getNavigationDefinition();
				if (currentScreenNavigationDefinition == null) {
					break;
				}
				exitCurrentScreen(terminalSession, currentEntityClass, currentScreenNavigationDefinition);
				snapshot = terminalSession.getSnapshot();
				currentEntityClass = screensRecognizer.match(snapshot);
				currentEntityDefinition = screenEntitiesRegistry.get(currentEntityClass);
			}
		}

		if (navigationSteps == null) {
			ScreenNavigationUtil.validateCurrentScreen(targetScreenEntity, currentEntityClass);
		}

		navigationMetadata.add(currentEntityDefinition, targetEntityDefinition, navigationSteps);

		performDirectNavigation(terminalSession, currentEntityClass, navigationSteps);
	}

	private static void exitCurrentScreen(TerminalSession terminalSession, Class<?> currentEntityClass,
			NavigationDefinition currentScreenNavigationDefinition) {
		if (logger.isDebugEnabled()) {
			logger.debug(MessageFormat.format("Steping back of screen {0} using {1}", currentEntityClass,
					currentScreenNavigationDefinition.getExitAction()));
		}
		terminalSession.doAction(currentScreenNavigationDefinition.getExitAction());
	}

	private static void performDirectNavigation(TerminalSession terminalSession, Class<?> currentEntityClass,
			List<NavigationDefinition> navigationSteps) {
		Collections.reverse(navigationSteps);
		ScreenEntity currentEntity = terminalSession.getEntity();
		for (NavigationDefinition navigationDefinition : navigationSteps) {
			ScreenPojoFieldAccessor fieldAccessor = new SimpleScreenPojoFieldAccessor(currentEntity);
			List<FieldAssignDefinition> assignedFields = navigationDefinition.getAssignedFields();
			if (logger.isDebugEnabled()) {
				currentEntityClass = ProxyUtil.getOriginalClass(currentEntity.getClass());
				logger.debug("Performing navigation actions from screen " + currentEntityClass);
			}
			for (FieldAssignDefinition fieldAssignDefinition : assignedFields) {
				fieldAccessor.setFieldValue(fieldAssignDefinition.getName(), fieldAssignDefinition.getValue());
			}
			terminalSession.doAction(navigationDefinition.getTerminalAction(), currentEntity);
			currentEntity = terminalSession.getEntity();
		}
	}

	/**
	 * Look for a direct path from the target screen back to the current screen
	 */
	private List<NavigationDefinition> findDirectNavigationPath(ScreenEntityDefinition sourceEntityDefinition,
			ScreenEntityDefinition targetEntityDefinition) {
		Class<?> currentNavigationNode = targetEntityDefinition.getEntityClass();
		NavigationDefinition navigationDefinition = targetEntityDefinition.getNavigationDefinition();

		if (navigationDefinition == null) {
			return null;
		}

		List<NavigationDefinition> navigationSteps = new ArrayList<NavigationDefinition>();
		navigationSteps.add(navigationDefinition);
		while (currentNavigationNode != null) {
			currentNavigationNode = navigationDefinition.getAccessedFrom();
			ScreenEntityDefinition screenEntityDefinition = screenEntitiesRegistry.get(currentNavigationNode);
			navigationDefinition = screenEntityDefinition.getNavigationDefinition();
			if (screenEntityDefinition.getEntityClass() == sourceEntityDefinition.getEntityClass()) {
				// target reached
				break;
			}
			// no navigation definition found, exit
			if (navigationDefinition == null) {
				return null;
			}

			navigationSteps.add(navigationDefinition);
		}
		if (currentNavigationNode != null) {
			return navigationSteps;
		}
		return null;
	}

}