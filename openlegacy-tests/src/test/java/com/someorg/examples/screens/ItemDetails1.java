package com.someorg.examples.screens;

import org.openlegacy.FetchMode;
import org.openlegacy.annotations.screen.ChildScreenEntity;
import org.openlegacy.annotations.screen.FieldMapping;
import org.openlegacy.annotations.screen.Identifier;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.SimpleScreenIdentifiers;
import org.openlegacy.terminal.actions.SendKeyClasses;

@ScreenEntity
@SimpleScreenIdentifiers(identifiers = { @Identifier(row = 6, column = 2, value = "Item Number . ."),
		@Identifier(row = 7, column = 2, value = "Item Description") })
public class ItemDetails1 {

	@FieldMapping(row = 6, column = 33)
	private String itemNumber;

	@ChildScreenEntity(stepInto = SendKeyClasses.ENTER.class, fetchMode = FetchMode.LAZY)
	private ItemDetails2 itemDetails2;

}
