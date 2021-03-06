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

/**
 * A row part represents the content of a part of the row. A row part is typically split by editable property of
 * {@link TerminalField}, whitin a {@link TerminalRow}
 * 
 * @author Roi Mor
 */
public interface RowPart {

	TerminalPosition getPosition();

	String getValue();

	int getLength();

	boolean isEditable();
}
