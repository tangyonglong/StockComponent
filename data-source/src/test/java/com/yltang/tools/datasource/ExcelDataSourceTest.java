package com.yltang.tools.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yltang.tools.datasource.core.DataSourceFactory;
import com.yltang.tools.datasource.core.FileDataSource;


public class ExcelDataSourceTest {
	
	@Test 
	public void testReadExcelFile(){
		String filePath = null;
		DataSource<People> people = null;
		filePath = ExcelDataSourceTest.class.getResource("people.xlsx").getPath();
		
		try {
			people = DataSourceFactory.buildDataSource(People.class);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(FileDataSource.FILE_PATH, filePath);
			people.openDataSource(params);
			List<People> users = people.readData(2, 4);
			System.out.println(users);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(people != null){
				try {
					people.closeDataSource();
				} catch (Exception e) {}
			}
		}
	}
	
	@Test
	public void testReadExcelDirect(){
		String filePath = null;
		DataSource<People> people = null;
		filePath = ExcelDataSourceTest.class.getResource("people.xlsx").getPath().replace("people.xlsx", "");
		
		try {
			people = DataSourceFactory.buildDataSource(People.class);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(FileDataSource.FILE_PATH, filePath);
			people.openDataSource(params);
			List<People> users = people.readData(2, 6);
			System.out.println(users);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(people != null){
				try {
					people.closeDataSource();
				} catch (Exception e) {}
			}
		}
	}
}
