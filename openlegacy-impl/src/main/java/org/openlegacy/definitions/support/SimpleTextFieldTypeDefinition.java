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
package org.openlegacy.definitions.support;

import org.openlegacy.definitions.FieldTypeDefinition;

import java.io.Serializable;

public class SimpleTextFieldTypeDefinition implements FieldTypeDefinition, Serializable {

	private static final long serialVersionUID = 1L;

	public String getTypeName() {
		return "String";
	}

}
