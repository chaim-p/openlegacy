package org.openlegacy.terminal.samples.model;

import org.openlegacy.annotations.screen.Action;
import org.openlegacy.annotations.screen.AssignedField;
import org.openlegacy.annotations.screen.Identifier;
import org.openlegacy.annotations.screen.ScreenActions;
import org.openlegacy.annotations.screen.ScreenColumn;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenField;
import org.openlegacy.annotations.screen.ScreenIdentifiers;
import org.openlegacy.annotations.screen.ScreenNavigation;
import org.openlegacy.annotations.screen.ScreenTable;
import org.openlegacy.annotations.screen.ScreenTableActions;
import org.openlegacy.annotations.screen.TableAction;
import org.openlegacy.modules.table.RecordSelectionEntity;
import org.openlegacy.terminal.actions.TerminalActions;

import java.util.List;

@ScreenEntity(screenType = RecordSelectionEntity.class)
@ScreenIdentifiers(identifiers = {
		@Identifier(row = 2, column = 26, value = "  Work with Warehouse Details "),
		@Identifier(row = 4, column = 2, value = "Type one or more action codes. Then Enter.                                    ") })
@ScreenActions(actions = { @Action(action = TerminalActions.F1.class, displayName = "Help", alias = "help"),
		@Action(action = TerminalActions.F3.class, displayName = "Exit", alias = "exit"),
		@Action(action = TerminalActions.F6.class, displayName = "Create", alias = "create"),
		@Action(action = TerminalActions.F12.class, displayName = "Cancel", alias = "cancel") })
@ScreenNavigation(accessedFrom = InventoryManagement.class, assignedFields = { @AssignedField(field = "menuSelection", value = "2") })
public class WorkWithWarehouseDetails {

	@ScreenField(row = 21, column = 18, endColumn = 20, labelColumn = 2, editable = true, displayName = "Position to")
	private String positionTo;

	private List<WorkWithWarehouseDetailsRecord> workWithWarehouseDetailsRecords;

	@ScreenTable(startRow = 8, endRow = 19)
	@ScreenTableActions(actions = { @TableAction(actionValue = "2", displayName = "Revise"),
			@TableAction(actionValue = "4", displayName = "Delete"), @TableAction(actionValue = "5", displayName = "Display"),
			@TableAction(actionValue = "6", displayName = "Items"), @TableAction(actionValue = "7", displayName = "Locations") })
	public static class WorkWithWarehouseDetailsRecord {

		@ScreenColumn(startColumn = 4, endColumn = 4, editable = true, selectionField = true, displayName = "Action")
		private Integer action_;

		@ScreenColumn(startColumn = 10, endColumn = 12, key = true, displayName = "Whse", sampleValue = "001")
		private Integer whse;

		@ScreenColumn(startColumn = 16, endColumn = 55, mainDisplayField = true, displayName = "Description", sampleValue = "Big meat warehouse")
		private String description;

	}

}