package org.openlegacy.designtime.analyzer.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.Snapshot;
import org.openlegacy.designtime.analyzer.SnapshotsSimilarityChecker;
import org.openlegacy.designtime.analyzer.SnapshotsSorter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public abstract class AbstractSnapshotsSorter<S extends Snapshot> implements SnapshotsSorter<S> {

	@Inject
	private SnapshotsSimilarityChecker<S> snapshotsSimilarityChecker;

	private Collection<Set<S>> snapshotGroups = new ArrayList<Set<S>>();

	private int matchingPercent = 99;

	private final static Log logger = LogFactory.getLog(AbstractSnapshotsSorter.class);

	public Collection<Set<S>> getGroups() {
		return Collections.unmodifiableCollection(snapshotGroups);
	}

	public Collection<S> getGroupsReprensters() {
		Collection<Set<S>> groups = getGroups();
		List<S> result = new ArrayList<S>();
		for (Set<S> group : groups) {
			S snapshot = pickRepresenter(group);
			result.add(snapshot);
		}
		return result;
	}

	protected abstract S pickRepresenter(Set<S> group);

	public void add(Collection<S> snapshots) {
		for (S snapshot : snapshots) {
			addSnapshotToGroup(snapshot);
		}

	}

	private void addSnapshotToGroup(S snapshot) {
		Collection<Set<S>> groups = getGroups();
		boolean found = false;
		for (Set<S> group : groups) {
			S groupFirstSnapshot = group.iterator().next();
			int snapshotsSimilarityPercent = snapshotsSimilarityChecker.similarityPercent(snapshot, groupFirstSnapshot);
			if (snapshotsSimilarityPercent > matchingPercent) {
				if (logger.isTraceEnabled() && snapshotsSimilarityPercent < 100) {
					logger.trace("\n*************************************************************************************");
					logger.trace("Matched snapshot to group by " + snapshotsSimilarityPercent);
					logger.trace("Snapshot:\n" + snapshot);
					logger.trace("Group representive snapshot:\n" + groupFirstSnapshot);
				}
				group.add(snapshot);
				found = true;
			}
		}
		if (!found) {
			Set<S> group = new HashSet<S>();
			group.add(snapshot);
			snapshotGroups.add(group);
		}
	}

	public void setMatchingPercent(int matchingPercent) {
		this.matchingPercent = matchingPercent;
	}
}
