package org.openlegacy.adapter.terminal;

import org.openlegacy.terminal.FieldMappingDefinition;
import org.openlegacy.terminal.FieldMappingsDefinitionProvider;
import org.openlegacy.terminal.TerminalScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * A FieldMappingsProvider based on open legacy @FieldMapping annotation kept in ScreenEntitiesRegistry
 * 
 */
public class RegistryBasedFieldMappingsProvider implements FieldMappingsDefinitionProvider {

	@Autowired
	private SimpleScreenEntitiesRegistry screenEntitiesRegistry;

	public Collection<FieldMappingDefinition> getFieldsMappingDefinitions(TerminalScreen terminalScreen, Class<?> screenEntity) {
		return screenEntitiesRegistry.getFieldsMappings(screenEntity);
	}
}
