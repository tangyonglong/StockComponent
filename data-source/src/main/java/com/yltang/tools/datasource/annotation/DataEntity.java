package com.yltang.tools.datasource.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataEntity {
	
	String value();
	
}
