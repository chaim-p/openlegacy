package org.openlegacy.support;

import org.openlegacy.Session;
import org.openlegacy.SessionProperties;
import org.openlegacy.SessionsManager;
import org.openlegacy.SessionsRegistry;
import org.openlegacy.Snapshot;
import org.openlegacy.modules.trail.SessionTrail;
import org.openlegacy.modules.trail.Trail;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

public class DefaultSessionsManager<S extends Session> implements SessionsManager<S>, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private transient SessionsRegistry<S> sessionsRegistry;

	public Set<SessionProperties> getSessionsProperties() {
		Set<SessionProperties> set = new TreeSet<SessionProperties>();
		set.addAll(sessionsRegistry.getSessionsProperties());
		return set;
	}

	public void disconnect(String sessionId) {
		getSession(sessionId).disconnect();
	}

	@SuppressWarnings("unchecked")
	public <SN extends Snapshot> SessionTrail<SN> getTrail(String sessionId) {
		return (SessionTrail<SN>)getSession(sessionId).getModule(Trail.class).getSessionTrail();
	}

	private S getSession(String sessionId) {
		return sessionsRegistry.getSession(sessionId);
	}

	public SessionProperties getSessionProperties(String sessionId) {
		return getSession(sessionId).getProperties();
	}

}
