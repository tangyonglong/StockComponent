package com.yltang.tools.datasource;

import java.util.Date;

import com.yltang.tools.datasource.annotation.DataEntity;
import com.yltang.tools.datasource.annotation.DataOrigin;
import com.yltang.tools.datasource.annotation.FieldMapping;
import com.yltang.tools.datasource.entity.DataOriginType;
import com.yltang.tools.datasource.entity.FieldType;

@DataEntity("用户")
@DataOrigin(DataOriginType.EXCEL)
public class People {

	@FieldMapping(sourceName="姓名", type = FieldType.STRING)
	private String username;
	
	@FieldMapping(sourceName="年龄", type = FieldType.INT)
	private int age;
	
	@FieldMapping(sourceName="手机号码", type = FieldType.STRING)
	private String telNo;

	@FieldMapping(sourceName="身高", type = FieldType.FLOAT)
	private float height;
	
	@FieldMapping(sourceName="出生日期", type = FieldType.Date)
	private Date birthday;

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

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
