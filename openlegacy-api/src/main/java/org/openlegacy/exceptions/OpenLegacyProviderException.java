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
package org.openlegacy.exceptions;

/**
 * Wrapper exception for providers exception
 * 
 */
public class OpenLegacyProviderException extends OpenLegacyRuntimeException {

	private static final long serialVersionUID = 1L;

	public OpenLegacyProviderException(Exception e) {
		super(e);
	}

	public OpenLegacyProviderException(String s) {
		super(s);
	}

	public OpenLegacyProviderException(String s, Exception e) {
		super(s, e);
	}
}
