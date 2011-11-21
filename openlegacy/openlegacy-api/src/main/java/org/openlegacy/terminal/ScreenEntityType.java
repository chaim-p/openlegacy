package org.openlegacy.terminal;

import org.openlegacy.EntityType;

/**
 * Screen entity type define the business purpose of the entity. Example usage: Login screen, menu screen, etc
 * 
 * ScreenEntityType may be used by session modules which are interested of understand the legacy application entities/screens, by
 * querying the registry
 * 
 * It is possible to define more screen entity types by implementing this interface
 */
public interface ScreenEntityType extends EntityType {

	public static class General implements ScreenEntityType {
	}
}
