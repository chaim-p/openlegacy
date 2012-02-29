package org.openlegacy.designtime.terminal.generators.support;

import org.openlegacy.annotations.screen.ScreenActions;
import org.openlegacy.annotations.screen.ScreenColumn;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenEntitySuperClass;
import org.openlegacy.annotations.screen.ScreenField;
import org.openlegacy.annotations.screen.ScreenPart;
import org.openlegacy.annotations.screen.ScreenTable;

public class AnnotationConstants {

	public static final String SCREEN_ENTITY_ANNOTATION = ScreenEntity.class.getSimpleName();
	public static final String SCREEN_ENTITY_SUPER_CLASS_ANNOTATION = ScreenEntitySuperClass.class.getSimpleName();
	public static final String SCREEN_PART_ANNOTATION = ScreenPart.class.getSimpleName();
	public static final String SCREEN_TABLE_ANNOTATION = ScreenTable.class.getSimpleName();
	public static final String SCREEN_COLUMN_ANNOTATION = ScreenColumn.class.getSimpleName();
	public static final String TRUE = "true";
	public static final String FIELD_SUFFIX = "Field";
	public static final String SCREEN_FIELD_ANNOTATION = ScreenField.class.getSimpleName();
	public static final Object SCREEN_ACTIONS_ANNOTATION = ScreenActions.class.getSimpleName();

	public static final String SUPPORT_TERMINAL_DATA = "supportTerminalData";
	public static final String EDITABLE = "editable";
	public static final String ROW = "row";
	public static final String COLUMN = "column";
	public static final String DISPLAY_NAME = "displayName";
	public static final String NAME = "name";
	public static final String START_COLUMN = "startColumn";
	public static final String END_COLUMN = "endColumn";
	public static final String START_ROW = "startRow";
	public static final String END_ROW = "endRow";
	public static final String ACTION = "action";
	public static final String ALIAS = "alias";
	public static final String LABEL_COLUMN = "labelColumn";
	public static final String SCREEN_TYPE = "screenType";
}