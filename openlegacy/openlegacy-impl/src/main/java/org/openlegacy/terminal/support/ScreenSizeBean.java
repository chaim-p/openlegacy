package org.openlegacy.terminal.support;

import org.openlegacy.terminal.ScreenSize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * A simple definition for screen size model with rows & columns properties
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ScreenSizeBean implements ScreenSize {

	@XmlAttribute
	private int rows;
	@XmlAttribute
	private int columns;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

}
