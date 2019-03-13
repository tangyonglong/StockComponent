package com.yltang.tools.datasource.entity;

public enum DataOriginType {

	EXCEL("com.yltang.tools.datasource.core.ExcelDataSource"), //数据来源Excel
	TXT("com.yltang.tools.datasource.core.TxtDataSource");//数据来源txt文本
	
	private String val;
	
	private DataOriginType(String val){
		this.val = val;
	}
	
	public String getVal(){
		return this.val;
	}
	
}
