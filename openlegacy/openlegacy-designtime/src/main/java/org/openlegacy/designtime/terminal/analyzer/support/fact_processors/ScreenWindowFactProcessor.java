package org.openlegacy.designtime.terminal.analyzer.support.fact_processors;

import org.openlegacy.designtime.terminal.analyzer.ScreenFact;
import org.openlegacy.designtime.terminal.analyzer.ScreenFactProcessor;
import org.openlegacy.designtime.terminal.model.ScreenEntityDesigntimeDefinition;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalPosition;
import org.openlegacy.terminal.TerminalRectangle;
import org.openlegacy.terminal.support.SimpleTerminalPosition;
import org.openlegacy.terminal.support.SimpleTerminalRectangle;

public class ScreenWindowFactProcessor implements ScreenFactProcessor {

	public boolean accept(ScreenFact screenFact) {
		return screenFact instanceof ScreenWindowFact;
	}

	public void process(ScreenEntityDesigntimeDefinition screenEntityDefinition, ScreenFact screenFact) {
		ScreenWindowFact screenWindowFact = (ScreenWindowFact)screenFact;
		TerminalField buttomBorderField = screenWindowFact.getButtomBorderField();
		TerminalField topBorderField = screenWindowFact.getTopBorderField();

		TerminalPosition buttomLeftPosition = new SimpleTerminalPosition(buttomBorderField.getPosition().getRow(),
				buttomBorderField.getPosition().getColumn() + buttomBorderField.getLength());
		TerminalRectangle borders = new SimpleTerminalRectangle(topBorderField.getPosition(), buttomLeftPosition);
		screenEntityDefinition.setSnapshotBorders(borders);
	}

}