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
package org.openlegacy.terminal;

import java.io.Serializable;

/**
 * A simple definition for screen size model with rows & columns properties
 * 
 */
public interface ScreenSize extends Serializable {

	public static final int DEFAULT_ROWS = 24;
	public static final int DEFAULT_COLUMN = 80;

	int getRows();

	int getColumns();

	boolean contains(TerminalPosition terminalPosition);
}
