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
package org.openlegacy.terminal.support.binders;

import org.openlegacy.definitions.EnumFieldTypeDefinition;
import org.openlegacy.exceptions.EntityNotFoundException;
import org.openlegacy.terminal.FieldAttributeType;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.ScreenEntityBinder;
import org.openlegacy.terminal.ScreenPojoFieldAccessor;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalPosition;
import org.openlegacy.terminal.TerminalSendAction;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.ScreenFieldDefinition;
import org.openlegacy.terminal.exceptions.ScreenEntityNotAccessibleException;
import org.openlegacy.terminal.providers.ScreenFieldsDefinitionProvider;
import org.openlegacy.terminal.support.SimpleTerminalPosition;
import org.openlegacy.terminal.utils.SimpleScreenPojoFieldAccessor;
import org.openlegacy.utils.ProxyUtil;
import org.openlegacy.utils.ReflectionUtil;
import org.openlegacy.utils.StringUtil;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Inject;

/**
 * Enum binder implementation for binding enum based fields to a screenEntity based on the enum getValue property
 * 
 * @author Roi Mor
 */
public class EnumFieldsBinder implements ScreenEntityBinder, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ScreenFieldsDefinitionProvider fieldMappingsProvider;

	public void populateEntity(Object screenEntity, TerminalSnapshot terminalSnapshot) throws EntityNotFoundException,
			ScreenEntityNotAccessibleException {

		ScreenPojoFieldAccessor fieldAccessor = null;

		Class<? extends Object> class1 = ProxyUtil.getOriginalClass(screenEntity.getClass());

		Collection<ScreenFieldDefinition> fieldDefinitions = fieldMappingsProvider.getFieldsMappingDefinitions(terminalSnapshot,
				class1);

		for (ScreenFieldDefinition fieldDefinition : fieldDefinitions) {
			if (!Enum.class.isAssignableFrom(fieldDefinition.getJavaType())
					|| fieldDefinition.getAttribute() != FieldAttributeType.Value) {
				continue;
			}
			// lazy creation - mostly not used
			if (fieldAccessor == null) {
				fieldAccessor = new SimpleScreenPojoFieldAccessor(screenEntity);
			}
			EnumFieldTypeDefinition fieldTypeDefinition = (EnumFieldTypeDefinition)fieldDefinition.getFieldTypeDefinition();
			TerminalPosition position = fieldDefinition.getPosition();

			TerminalField enumField = terminalSnapshot.getField(SimpleTerminalPosition.newInstance(position.getRow(),
					position.getColumn()));

			if (!StringUtil.isEmpty(enumField.getValue())) {

				// find the DisplayItem by host value, and return the enum object
				fieldAccessor.setFieldValue(fieldDefinition.getName(),
						fieldTypeDefinition.getEnums().get(enumField.getValue()).getValue());
			}

		}
	}

	public void populateSendAction(TerminalSendAction sendAction, TerminalSnapshot terminalSnapshot, Object entity) {

		if (entity == null) {
			return;
		}

		Assert.isTrue(entity instanceof ScreenEntity, "screen entity must implement ScreenEntity interface");

		ScreenEntity screenEntity = (ScreenEntity)entity;

		Collection<ScreenFieldDefinition> fieldDefinitions = fieldMappingsProvider.getFieldsMappingDefinitions(terminalSnapshot,
				screenEntity.getClass());

		if (fieldDefinitions == null) {
			return;
		}

		ScreenPojoFieldAccessor fieldAccessor = null;

		for (ScreenFieldDefinition fieldDefinition : fieldDefinitions) {
			if (!Enum.class.isAssignableFrom(fieldDefinition.getJavaType())) {
				continue;
			}

			// lazy creation - mostly not used
			if (fieldAccessor == null) {
				fieldAccessor = new SimpleScreenPojoFieldAccessor(screenEntity);
			}

			EnumFieldTypeDefinition fieldTypeDefinition = (EnumFieldTypeDefinition)fieldDefinition.getFieldTypeDefinition();
			TerminalPosition position = fieldDefinition.getPosition();
			TerminalField enumField = terminalSnapshot.getField(SimpleTerminalPosition.newInstance(position.getRow(),
					position.getColumn()));

			Object fieldValue = fieldAccessor.getFieldValue(fieldDefinition.getName());

			Collection<Object> enums = fieldTypeDefinition.getDisplayValues().keySet();
			for (Object enumValue : enums) {
				if (enumValue.equals(fieldValue)) {
					// once enum from the POJO is found, find the associated host value
					enumField.setValue((String)ReflectionUtil.invoke(enumValue, "getValue"));
				}
			}
			sendAction.getModifiedFields().add(enumField);
		}
	}

}
