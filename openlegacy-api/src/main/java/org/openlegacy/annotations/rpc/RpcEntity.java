package org.openlegacy.annotations.rpc;

import org.openlegacy.annotations.screen.AnnotationConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RpcEntity {

	/**
	 * Path to the RPC program
	 * 
	 * @return path
	 */
	String value();

	String displayName() default AnnotationConstants.NULL;
}
