/*******************************************************************************
 * Copyright (c) 2012 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 *******************************************************************************/
package org.openlegacy.designtime.terminal.analyzer.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.designtime.analyzer.SnapshotsAnalyzerContext;
import org.openlegacy.designtime.terminal.model.ScreenEntityDesigntimeDefinition;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.TerminalSnapshot.SnapshotType;
import org.openlegacy.terminal.definitions.ScreenEntityDefinition;
import org.openlegacy.terminal.services.ScreenIdentification;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Component
@Scope("prototype")
public class TerminalSnapshotsAnalyzerContext implements SnapshotsAnalyzerContext<TerminalSnapshot, ScreenEntityDefinition> {

	private static final String SCREEN = "Screen";
	private Collection<TerminalSnapshot> activeSnapshots;

	// holds all entity definitions during analysis
	private Map<String, List<ScreenEntityDesigntimeDefinition>> entitiesDefinitions = new HashMap<String, List<ScreenEntityDesigntimeDefinition>>();

	// The resulting entity definitions
	private Map<String, ScreenEntityDefinition> entitiesDefinitionsResult;

	// entity definitions Organized by identification
	private Map<ScreenIdentification, ScreenEntityDefinition> entitiesDefinitionsByIdentification;

	private final static Log logger = LogFactory.getLog(TerminalSnapshotsAnalyzerContext.class);

	public Collection<TerminalSnapshot> getActiveSnapshots() {
		return activeSnapshots;
	}

	public void setActiveSnapshots(Collection<TerminalSnapshot> snapshots) {
		clear();
		activeSnapshots = snapshots;

	}

	public void addEntityDefinition(String desiredEntityName, ScreenEntityDesigntimeDefinition screenEntityDefinition) {

		if (desiredEntityName == null) {
			desiredEntityName = suggestEntityName(screenEntityDefinition);
		}

		List<ScreenEntityDesigntimeDefinition> matchingDefinitions = entitiesDefinitions.get(desiredEntityName);
		if (matchingDefinitions == null) {
			matchingDefinitions = new ArrayList<ScreenEntityDesigntimeDefinition>();
			entitiesDefinitions.put(desiredEntityName, matchingDefinitions);
		}
		matchingDefinitions.add(screenEntityDefinition);

	}

	private String suggestEntityName(ScreenEntityDefinition screenEntityDefinition) {
		String desiredEntityName;
		Integer sequence = screenEntityDefinition.getSnapshot().getSequence();
		if (sequence != null) {
			desiredEntityName = SCREEN + sequence;
		} else {
			desiredEntityName = SCREEN + entitiesDefinitions.size();
		}
		return desiredEntityName;
	}

	private static String findFreeEntityName(String entityName, Map<String, ScreenEntityDefinition> entitiesDefinitions) {
		int nameCount = 1;
		String tempEntityName = entityName;
		String baseEntityName = entityName;
		while (entitiesDefinitions.get(tempEntityName) != null) {
			tempEntityName = baseEntityName + nameCount++;
		}
		return tempEntityName;
	}

	public Map<String, ScreenEntityDefinition> getEntitiesDefinitions() {
		return entitiesDefinitionsResult;
	}

	/**
	 * Once all screen entities definitions are requested, flatten all the entities, and provide each entity definition a unique
	 * name.
	 * 
	 */
	public void finalizeEntitiesDefinitions() {
		Set<Entry<String, List<ScreenEntityDesigntimeDefinition>>> entitiesByNameDefiniton = entitiesDefinitions.entrySet();
		entitiesDefinitionsResult = new HashMap<String, ScreenEntityDefinition>();
		// used to avoid building 2 screen entities with the same identifiers
		entitiesDefinitionsByIdentification = new HashMap<ScreenIdentification, ScreenEntityDefinition>();

		for (Entry<String, List<ScreenEntityDesigntimeDefinition>> entry : entitiesByNameDefiniton) {
			List<ScreenEntityDesigntimeDefinition> definitions = entry.getValue();
			// when combining few screen definition with the same desired entity name, give priority to entity with a lower
			// snapshot sequence
			Collections.sort(definitions, TerminalSnapshotSequenceComparator.instance());

			for (ScreenEntityDesigntimeDefinition screenEntityDefinition : definitions) {
				// verify no 2 screens with same identifiers are added (SimpleScreenIdentification.hashCode)
				if (!entitiesDefinitionsByIdentification.containsKey(screenEntityDefinition.getScreenIdentification())) {
					String actualEntityName = findFreeEntityName(entry.getKey(), entitiesDefinitionsResult);
					screenEntityDefinition.setEntityName(actualEntityName);
					entitiesDefinitionsResult.put(actualEntityName, screenEntityDefinition);
					entitiesDefinitionsByIdentification.put(screenEntityDefinition.getScreenIdentification(),
							screenEntityDefinition);
				}
			}
		}
	}

	/**
	 * Pick the snapshots which was accessed from each incoming snapshot. The active snapshots holds all the provided snapshot to
	 * the snapshots analyzer
	 */
	public Collection<TerminalSnapshot> getAccessedFromSnapshots(Collection<TerminalSnapshot> incomingSnapshots) {

		List<TerminalSnapshot> outgoingSnapshots = new ArrayList<TerminalSnapshot>();

		for (TerminalSnapshot incomingSnapshot : incomingSnapshots) {
			if (incomingSnapshot.getSequence() == null) {
				logger.debug("Ignoring outgoing snapshot since it has no sequence");
				continue;
			}
			for (TerminalSnapshot activeSnapshot : activeSnapshots) {
				if (activeSnapshot.getSequence() == null) {
					logger.debug("Ignoring active snapshot since it has no sequence");
					continue;
				}
				if (incomingSnapshot.getSequence() - 1 == activeSnapshot.getSequence()
						&& activeSnapshot.getSnapshotType() == SnapshotType.OUTGOING) {
					outgoingSnapshots.add(activeSnapshot);
				}
			}
		}
		return outgoingSnapshots;
	}

	public static class TerminalSnapshotSequenceComparator implements Comparator<ScreenEntityDefinition> {

		private static TerminalSnapshotSequenceComparator instance = new TerminalSnapshotSequenceComparator();

		public static TerminalSnapshotSequenceComparator instance() {
			return instance;
		}

		public int compare(ScreenEntityDefinition o1, ScreenEntityDefinition o2) {
			Integer sequence1 = o1.getSnapshot().getSequence();
			Integer sequence2 = o2.getSnapshot().getSequence();
			if (sequence1 == null || sequence2 == null) {
				return 0;
			}
			return sequence1 - sequence2;
		}

	}

	public TerminalSnapshot getOutgoingSnapshot(TerminalSnapshot incomingSnapshot) {
		if (incomingSnapshot.getSequence() == null) {
			logger.debug("Ignoring outgoing snapshot since it has no sequence");
			return null;
		}
		for (TerminalSnapshot activeSnapshot : activeSnapshots) {
			if (activeSnapshot.getSequence() == null) {
				logger.debug("Ignoring active snapshot since it has no sequence");
				continue;
			}
			if (incomingSnapshot.getSequence() + 1 == activeSnapshot.getSequence()
					&& activeSnapshot.getSnapshotType() == SnapshotType.OUTGOING) {
				return activeSnapshot;
			}
		}
		return null;
	}

	private void clear() {
		entitiesDefinitions.clear();
		if (entitiesDefinitionsResult != null) {
			entitiesDefinitionsResult.clear();
		}
		if (entitiesDefinitionsResult != null) {
			entitiesDefinitionsByIdentification.clear();
		}
	}
}
