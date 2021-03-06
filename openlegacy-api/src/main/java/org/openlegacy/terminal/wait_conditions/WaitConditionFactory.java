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
package org.openlegacy.terminal.wait_conditions;

public interface WaitConditionFactory {

	/**
	 * Initialize the given wait condition class with factory defaults and the given args. The waitClass should have a constructor
	 * with a matching argument types
	 * 
	 * @param waitClass
	 * @param args
	 * @return
	 */
	<T extends WaitCoditionAdapter> T create(Class<T> waitClass, Object... args);
}