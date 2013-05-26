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
package org.openlegacy.support;

import org.openlegacy.Session;
import org.openlegacy.SessionProperties;
import org.openlegacy.SessionPropertiesProvider;
import org.openlegacy.exceptions.OpenLegacyRuntimeException;
import org.openlegacy.modules.SessionModule;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.inject.Inject;

/**
 * An abstract session class which exposes modules management functionality
 * 
 * 
 */
public abstract class AbstractSession implements Session, InitializingBean, DisposableBean, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionModules sessionModules;

	@Inject
	private SessionPropertiesProvider sessionPropertiesProvider;

	private SessionProperties sessionProperties;

	@SuppressWarnings("unchecked")
	public <M extends SessionModule> M getModule(Class<M> module) {
		if (sessionModules == null) {
			throw (new OpenLegacyRuntimeException("No modules defined for session"));
		}
		List<? extends SessionModule> modules = sessionModules.getModules();
		for (SessionModule registeredModule : modules) {
			if (module.isAssignableFrom(registeredModule.getClass())) {
				return (M)registeredModule;
			}
		}
		throw (new OpenLegacyRuntimeException(MessageFormat.format("No module {0} defined for session", module)));
	}

	public void setSessionModules(SessionModules sessionModules) {
		this.sessionModules = sessionModules;
	}

	public SessionModules getSessionModules() {
		return sessionModules;
	}

	public void setSessionPropertiesProvider(SessionPropertiesProvider sessionPropertiesProvider) {
		this.sessionPropertiesProvider = sessionPropertiesProvider;
	}

	public SessionProperties getProperties() {
		if (sessionProperties == null) {
			sessionProperties = sessionPropertiesProvider.getSessionProperties();
		}
		return sessionProperties;
	}

	/**
	 * Pass the session to all the modules
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void afterPropertiesSet() throws Exception {
		List<? extends SessionModule> modules = sessionModules.getModules();
		for (SessionModule sessionModule : modules) {
			if (sessionModule instanceof SessionModuleAdapter) {
				((SessionModuleAdapter)sessionModule).setSession(this);
			}
		}
	}

	public void destroy() throws Exception {
		disconnect();
	}
}
