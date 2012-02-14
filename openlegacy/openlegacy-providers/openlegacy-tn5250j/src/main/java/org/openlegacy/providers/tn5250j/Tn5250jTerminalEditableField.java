package org.openlegacy.providers.tn5250j;

import org.openlegacy.terminal.support.SimpleTerminalPosition;
import org.openlegacy.utils.StringUtil;
import org.tn5250j.framework.tn5250.ScreenField;

public class Tn5250jTerminalEditableField extends Tn5250jTerminalField {

	private static final long serialVersionUID = 1L;

	private ScreenField screenField;

	public Tn5250jTerminalEditableField(ScreenField screenField, int fieldAttributes) {
		super(screenField.getString(), new SimpleTerminalPosition(screenField.startRow() + 1, screenField.startCol() + 1),
				screenField.getLength(), fieldAttributes);
		this.screenField = screenField;
	}

	@Override
	public String getValue() {
		if (getModifiedValue() != null) {
			return getModifiedValue();
		}
		return StringUtil.rightTrim(screenField.getString());
	}

	@Override
	public int getLength() {
		return screenField.getLength();
	}

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public Class<?> getType() {
		if (screenField.isNumeric()) {
			return Double.class;
		}
		return String.class;
	}
}
