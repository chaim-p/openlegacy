package org.openlegacy.designtime.terminal.analyzer.support.fact_processors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.definitions.support.SimpleDateFieldTypeDefinition;
import org.openlegacy.designtime.terminal.analyzer.ScreenFact;
import org.openlegacy.designtime.terminal.analyzer.ScreenFactProcessor;
import org.openlegacy.designtime.terminal.model.ScreenEntityDesigntimeDefinition;
import org.openlegacy.terminal.definitions.ScreenFieldDefinition;
import org.openlegacy.terminal.definitions.SimpleScreenFieldDefinition;
import org.openlegacy.utils.StringUtil;

import java.text.MessageFormat;
import java.util.Date;

public class DateFieldFactProcessor implements ScreenFactProcessor {

	private final static Log logger = LogFactory.getLog(DateFieldFactProcessor.class);

	public boolean accept(ScreenFact screenFact) {
		return screenFact instanceof DateFieldFact;
	}

	public void process(ScreenEntityDesigntimeDefinition screenEntityDefinition, ScreenFact screenFact) {
		DateFieldFact dateFieldFact = (DateFieldFact)screenFact;

		SimpleScreenFieldDefinition leftFieldDefinition = (SimpleScreenFieldDefinition)dateFieldFact.getLeftField();

		ScreenFieldDefinition middleField = dateFieldFact.getMiddleField();
		ScreenFieldDefinition rightField = dateFieldFact.getRightField();

		SimpleDateFieldTypeDefinition fieldTypeDefinition = new SimpleDateFieldTypeDefinition(
				dateFieldFact.getLeftField().getPosition().getColumn(), middleField.getPosition().getColumn(),
				rightField.getPosition().getColumn());
		leftFieldDefinition.setFieldTypeDefinition(fieldTypeDefinition);
		leftFieldDefinition.setJavaType(Date.class);

		screenEntityDefinition.getReferredClasses().add(Date.class.getName());

		// remove all 3 fields date fields and add with the correct name. The middle/last date fields may take the label field
		// name as drools as can't verify analysis order
		screenEntityDefinition.getFieldsDefinitions().remove(leftFieldDefinition.getName());
		if (middleField != null) {
			screenEntityDefinition.getFieldsDefinitions().remove(middleField.getName());
		}
		if (rightField != null) {
			screenEntityDefinition.getFieldsDefinitions().remove(rightField.getName());
		}

		// set the length as all 3 - as place holder
		leftFieldDefinition.setLength(rightField.getEndPosition().getColumn() - leftFieldDefinition.getPosition().getColumn());

		// re-add the field
		String fieldName = StringUtil.toJavaFieldName(dateFieldFact.getLabelField().getValue());
		leftFieldDefinition.setName(fieldName);
		screenEntityDefinition.getFieldsDefinitions().put(fieldName, leftFieldDefinition);

		logger.info(MessageFormat.format("Set field {0} to be date field", leftFieldDefinition.getName()));
	}
}
