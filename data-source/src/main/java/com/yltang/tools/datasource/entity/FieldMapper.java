package com.yltang.tools.datasource.entity;


public class FieldMapper {

	private String sourceName;
	
	private String fieldName;
	
	private FieldType fieldType;
	
	private int index;
	
	public FieldMapper(String sourceName,String fieldName,FieldType fieldType){
		this.sourceName = sourceName;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.index = -1;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
