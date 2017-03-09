package com.wchy.common.convert;

import java.lang.annotation.Retention;



@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Mapper {

	//表的列名，可以不指定。
	String column() default "";
	
}
