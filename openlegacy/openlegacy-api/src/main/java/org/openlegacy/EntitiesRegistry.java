package org.openlegacy;

import org.openlegacy.definitions.FieldDefinition;
import org.openlegacy.exceptions.RegistryException;

import java.util.List;

/**
 * A common interface for defining a registry for entities, and retrieving an entity class by name
 * 
 */
public interface EntitiesRegistry<H extends EntityDefinition<D>, D extends FieldDefinition> {

	Class<?> getEntityClass(String entityName);

	String getEntityName(Class<?> entity);

	List<Class<?>> getByType(Class<? extends EntityType> entityType);

	H getSingleEntityDefinition(Class<? extends EntityType> entityType) throws RegistryException;

	List<Class<?>> getAll();

	void add(H entityDefinition);

	H get(Class<?> entityClass);

	void clear();
}