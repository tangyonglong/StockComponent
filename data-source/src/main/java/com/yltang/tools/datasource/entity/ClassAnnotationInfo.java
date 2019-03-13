package com.yltang.tools.datasource.entity;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 存放类的注解信息
 * */
public class ClassAnnotationInfo {

	/**
	 * 类型注解
	 * */
	private Map<String, Annotation[]> classAnnotations;
	
	/**
	 * 字段注解
	 * */
	private Map<String, Annotation[]> fieldAnnotations;
	
	/**
	 * 方法注解
	 * */
	private Map<String, Annotation[]> methodAnnotations;
	
	public ClassAnnotationInfo(Map<String, Annotation[]> clsAnnos, Map<String, Annotation[]> fieldAnnos, Map<String, Annotation[]> methodAnnos){
		this.classAnnotations = clsAnnos;
		this.fieldAnnotations = fieldAnnos;
		this.methodAnnotations = methodAnnos;
	}

	public Map<String, Annotation[]> getClassAnnotations() {
		return classAnnotations;
	}

	public void setClassAnnotations(Map<String, Annotation[]> classAnnotations) {
		this.classAnnotations = classAnnotations;
	}

	public Map<String, Annotation[]> getFieldAnnotations() {
		return fieldAnnotations;
	}

	public void setFieldAnnotations(Map<String, Annotation[]> fieldAnnotations) {
		this.fieldAnnotations = fieldAnnotations;
	}

	public Map<String, Annotation[]> getMethodAnnotations() {
		return methodAnnotations;
	}

	public void setMethodAnnotations(Map<String, Annotation[]> methodAnnotations) {
		this.methodAnnotations = methodAnnotations;
	}
}
