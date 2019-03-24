package com.yltang.tools.datasource.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public enum FieldType {

	INT {
		@Override
		public Object parse(Object obj) {
			return new Integer(String.valueOf(obj));
		}
	},
	STRING{

		@Override
		public Object parse(Object obj) {
			return String.valueOf(obj);
		}
	},
	FLOAT{
		@Override
		public Object parse(Object obj) {
			return new Float(String.valueOf(obj));
		}
	},
	DOUBLE{
		@Override
		public Object parse(Object obj) {
			return new Double(String.valueOf(obj));
		}
	},
	Date{
		@Override
		public Object parse(Object obj){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			try {
				return sdf.parse(String.valueOf(obj));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return obj;
		}
	};
	
	private FieldType(){}
	
	public abstract Object parse(Object obj);
}
