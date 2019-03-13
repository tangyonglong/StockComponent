package com.yltang.tools.datasource;

import java.util.List;
import java.util.Map;

public interface DataSource<T>{
	
	public void openDataSource(Map<String, Object> params)throws Exception;
	
	public List<T> readData(int page,int size) throws Exception;
	
	public void closeDataSource()throws Exception;
	
}
