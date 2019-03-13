package com.yltang.tools.datasource;

import com.yltang.tools.datasource.annotation.DataEntity;
import com.yltang.tools.datasource.annotation.DataOrigin;
import com.yltang.tools.datasource.annotation.FieldMapping;
import com.yltang.tools.datasource.entity.DataOriginType;
import com.yltang.tools.datasource.entity.FieldType;

@DataEntity("test")
@DataOrigin(DataOriginType.EXCEL)
public class PrUser {

	@FieldMapping(sourceName="姓名", type = FieldType.STRING)
	private String username;
	
	@FieldMapping(sourceName="年龄", type = FieldType.INT)
	private int age;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
