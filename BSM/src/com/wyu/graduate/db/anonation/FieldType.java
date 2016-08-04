package com.wyu.graduate.db.anonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldType {

		String  ColumnName() default "";
		boolean unique() default false;
		boolean notNUll() default false;
}



