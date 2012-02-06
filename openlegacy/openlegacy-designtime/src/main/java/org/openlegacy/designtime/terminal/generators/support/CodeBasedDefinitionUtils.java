package org.openlegacy.designtime.terminal.generators.support;

import org.openlegacy.designtime.generators.CodeBasedScreenPartDefinition;
import org.openlegacy.designtime.generators.CodeBasedScreenTableDefinition;
import org.openlegacy.designtime.terminal.generators.ScreenPojoCodeModel;
import org.openlegacy.designtime.terminal.generators.support.DefaultScreenPojoCodeModel.Field;
import org.openlegacy.designtime.utils.JavaParserUtil;
import org.openlegacy.terminal.definitions.ScreenEntityDefinition;
import org.openlegacy.terminal.definitions.ScreenFieldDefinition;
import org.openlegacy.terminal.definitions.SimpleScreenFieldDefinition;
import org.openlegacy.terminal.support.SimpleTerminalPosition;
import org.openlegacy.utils.StringUtil;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AnnotationExpr;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CodeBasedDefinitionUtils {

	public static Map<String, ScreenFieldDefinition> getFieldsFromCodeModel(ScreenPojoCodeModel codeModel, String containerPrefix) {

		// add prefix for field parts
		containerPrefix = !StringUtil.isEmpty(containerPrefix) ? containerPrefix + "." : "";

		Collection<Field> fields = codeModel.getFields();
		Map<String, ScreenFieldDefinition> fieldDefinitions = new TreeMap<String, ScreenFieldDefinition>();
		for (Field field : fields) {
			if (!field.isScreenField()) {
				continue;
			}
			SimpleScreenFieldDefinition fieldDefinition = new SimpleScreenFieldDefinition(containerPrefix + field.getName(), null);
			fieldDefinition.setPosition(new SimpleTerminalPosition(field.getRow(), field.getColumn()));
			fieldDefinition.setEditable(field.isEditable());
			fieldDefinition.setDisplayName(field.getDisplayName());
			if (field.getEndColumn() != null) {
				fieldDefinition.setLength(field.getEndColumn() - field.getColumn() + 1);
			}
			fieldDefinitions.put(field.getName(), fieldDefinition);
		}
		return fieldDefinitions;

	}

	public static ScreenEntityDefinition getEntityDefinition(CompilationUnit compilationUnit) {
		List<TypeDeclaration> types = compilationUnit.getTypes();
		CodeBasedScreenEntityDefinition screenDefinition = null;

		TypeDeclaration type = compilationUnit.getTypes().get(0);
		List<BodyDeclaration> members = type.getMembers();
		for (BodyDeclaration bodyDeclaration : members) {
			// look for inner classes
			if (bodyDeclaration instanceof ClassOrInterfaceDeclaration) {
				types.add((TypeDeclaration)bodyDeclaration);
			}
		}

		for (TypeDeclaration typeDeclaration : types) {
			List<AnnotationExpr> annotations = typeDeclaration.getAnnotations();
			if (annotations == null) {
				continue;
			}
			for (AnnotationExpr annotationExpr : annotations) {
				ScreenPojoCodeModel screenEntityCodeModel = null;
				if (JavaParserUtil.hasAnnotation(annotationExpr, AnnotationConstants.SCREEN_ENTITY_ANNOTATION)
						|| JavaParserUtil.hasAnnotation(annotationExpr, AnnotationConstants.SCREEN_ENTITY_SUPER_CLASS_ANNOTATION)) {
					screenEntityCodeModel = new DefaultScreenPojoCodeModel(compilationUnit,
							(ClassOrInterfaceDeclaration)typeDeclaration, typeDeclaration.getName());
					screenDefinition = new CodeBasedScreenEntityDefinition(screenEntityCodeModel);
				}
				if (JavaParserUtil.hasAnnotation(annotationExpr, AnnotationConstants.SCREEN_PART_ANNOTATION)) {
					screenEntityCodeModel = new DefaultScreenPojoCodeModel(compilationUnit,
							(ClassOrInterfaceDeclaration)typeDeclaration, typeDeclaration.getName());
					CodeBasedScreenPartDefinition partDefinition = new CodeBasedScreenPartDefinition(screenEntityCodeModel);
					screenDefinition.getPartsDefinitions().put(partDefinition.getPartName(), partDefinition);
				}
				if (JavaParserUtil.hasAnnotation(annotationExpr, AnnotationConstants.SCREEN_TABLE_ANNOTATION)) {
					screenEntityCodeModel = new DefaultScreenPojoCodeModel(compilationUnit,
							(ClassOrInterfaceDeclaration)typeDeclaration, typeDeclaration.getName());
					CodeBasedScreenTableDefinition tableDefinition = new CodeBasedScreenTableDefinition(screenEntityCodeModel);
					screenDefinition.getTableDefinitions().put(tableDefinition.getTableEntityName(), tableDefinition);
				}
			}
		}

		return screenDefinition;

	}
}
