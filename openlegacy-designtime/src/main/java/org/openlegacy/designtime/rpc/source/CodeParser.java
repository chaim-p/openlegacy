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
package org.openlegacy.designtime.rpc.source;

import org.openlegacy.rpc.definitions.RpcEntityDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import koopa.parsers.ParseResults;

public interface CodeParser {

	ParseResults parse(String source, String extension);

	RpcEntityDefinition getEntity(ParseResults parseResults, String extension);

	public ParseResults parse(String source, Map<String, InputStream> streamMap) throws IOException;

}
