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
package org.openlegacy.terminal.modules.trail;

import org.openlegacy.ApplicationConnection;
import org.openlegacy.modules.trail.SessionTrail;
import org.openlegacy.modules.trail.Trail;
import org.openlegacy.terminal.TerminalConnection;
import org.openlegacy.terminal.TerminalSendAction;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.support.SimpleTerminalOutgoingSnapshot;
import org.openlegacy.terminal.support.TerminalSessionModuleAdapter;

public class DefaultTerminalTrailModule extends TerminalSessionModuleAdapter implements Trail {

	private static final long serialVersionUID = 1L;

	private SessionTrail<TerminalSnapshot> sessionTrail;

	public SessionTrail<TerminalSnapshot> getSessionTrail() {
		return sessionTrail;
	}

	@Override
	public void beforeConnect(ApplicationConnection<?, ?> terminalConnection) {
		sessionTrail.clear();
	}

	@Override
	public void afterConnect(ApplicationConnection<?, ?> terminalConnection) {
		sessionTrail.appendSnapshot((TerminalSnapshot)terminalConnection.getSnapshot());
	}

	@Override
	public void beforeSendAction(TerminalConnection terminalConnection, TerminalSendAction terminalSendAction) {
		sessionTrail.appendSnapshot(new SimpleTerminalOutgoingSnapshot(terminalConnection.getSnapshot(), terminalSendAction));
	}

	@Override
	public void afterSendAction(TerminalConnection terminalConnection) {
		sessionTrail.appendSnapshot(terminalConnection.getSnapshot());
	}

	public void setSessionTrail(SessionTrail<TerminalSnapshot> sessionTrail) {
		this.sessionTrail = sessionTrail;
	}

}
