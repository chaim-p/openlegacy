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
package org.openlegacy.designtime.rpc.generators.support;

import org.openlegacy.annotations.rpc.RpcActions;
import org.openlegacy.annotations.rpc.RpcBooleanField;
import org.openlegacy.annotations.rpc.RpcEntity;
import org.openlegacy.annotations.rpc.RpcEntitySuperClass;
import org.openlegacy.annotations.rpc.RpcField;
import org.openlegacy.annotations.rpc.RpcNumericField;
import org.openlegacy.annotations.rpc.RpcPart;
import org.openlegacy.annotations.rpc.RpcPartList;

public class RpcAnnotationConstants {

	public static final String RPC_ENTITY_ANNOTATION = RpcEntity.class.getSimpleName();
	public static final String RPC_ENTITY_SUPER_CLASS_ANNOTATION = RpcEntitySuperClass.class.getSimpleName();
	public static final String RPC_PART_ANNOTATION = RpcPart.class.getSimpleName();
	public static final String RPC_ACTIONS_ANNOTATION = RpcActions.class.getSimpleName();
	public static final String RPC_TYPE = "rpcType";
	public static final String RPC_FIELD_ANNOTATION = RpcField.class.getSimpleName();
	public static final String RPC_PART_LIST = RpcPartList.class.getSimpleName();

	// @RpcEntity
	public static final String NAME = "name";
	public static final String DISPLAY_NAME = "displayName";
	public static final String LANGUAGE = "language";

	// @RpcNumericField
	public static final String MINIMUM_VALUE = "minimumValue";
	public static final String MAXIMUM_VALUE = "maximumValue";
	public static final String DECIMAL_PLACES = "decimalPlaces";

	// @RpcPart
	public static final String OCCUR = "occur";

	// @RpcField
	public static final String ORIGINAL_NAME = "originalName";
	public static final String DIRECTION = "direction";
	public static final String DEFAULT_VALUE = "defaultValue";
	public static final String COUNT = "count";
	public static final String RUNTIME_NAME = "runtimeName";

	public static final String RPC_BOOLEAN_FIELD_ANNOTATION = RpcBooleanField.class.getSimpleName();
	public static final String RPC_NUMERIC_ANNOTATION = RpcNumericField.class.getSimpleName();

	// @Action
	public static final String PATH = "path";

	// @RpcEntity
	public static final String NAME = "name";
	public static final String DISPLAY_NAME = "displayName";
	public static final String LANGUAGE = "language";
}
