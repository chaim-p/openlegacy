package org.openlegacy.ide.eclipse.preview;

import java.text.MessageFormat;

public class SelectedObject {

	private String displayName;
	private String fieldName;
	private FieldRectangle fieldRectangle;
	private boolean isEditable = false;
	private Integer labelColumn;

	public SelectedObject() {}

	public String getDisplayName() {
		return displayName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public FieldRectangle getFieldRectangle() {
		return fieldRectangle;
	}

	public Integer getLabelColumn() {
		return labelColumn;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setFieldRectangle(FieldRectangle fieldRectangle) {
		this.fieldRectangle = fieldRectangle;
	}

	public void setLabelColumn(Integer labelColumn) {
		this.labelColumn = labelColumn;
	}

	@Override
	public String toString() {
		return MessageFormat
				.format("fieldRectangle:[row: {0}, column: {1}, endRow: {2}, endColumn:{3}, value:{4}], displayName:[{5}], fieldName:[{6}], labelColumn:[{7}]",
						fieldRectangle.getRow(), fieldRectangle.getColumn(), fieldRectangle.getEndRow(),
						fieldRectangle.getEndColumn(), fieldRectangle.getValue(), displayName, fieldName, labelColumn);
	}

}
