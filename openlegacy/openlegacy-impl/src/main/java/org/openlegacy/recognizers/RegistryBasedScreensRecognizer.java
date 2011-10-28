package org.openlegacy.recognizers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.adapter.terminal.SimpleScreenEntitiesRegistry;
import org.openlegacy.terminal.ScreenEntityDefinition;
import org.openlegacy.terminal.TerminalScreen;
import org.openlegacy.terminal.spi.ScreensRecognizer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Registry based recognizer. Based on @ScreenEntity (identifier=...) definitions
 * 
 */
public class RegistryBasedScreensRecognizer implements ScreensRecognizer {

	@Autowired
	private SimpleScreenEntitiesRegistry screensRegistry;

	private final static Log logger = LogFactory.getLog(RegistryBasedScreensRecognizer.class);

	public Class<?> match(TerminalScreen terminalScreen) {
		ScreenEntityDefinition screenEntityDefinition = screensRegistry.match(terminalScreen);
		if (screenEntityDefinition != null && logger.isDebugEnabled()) {
			logger.debug("Screen matched by registry:" + screenEntityDefinition.getClass());
			return screenEntityDefinition.getHostEntityClass();
		}
		return null;
	}

}
