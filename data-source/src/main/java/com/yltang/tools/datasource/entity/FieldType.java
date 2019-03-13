package com.yltang.tools.datasource.entity;

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
	};
	
	private FieldType(){}
	
	public abstract Object parse(Object obj);
}
