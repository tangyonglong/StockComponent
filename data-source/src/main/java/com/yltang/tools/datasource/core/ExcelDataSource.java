package com.yltang.tools.datasource.core;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.yltang.tools.datasource.entity.FieldMapper;
import com.yltang.tools.datasource.entity.FileInfo;

public class ExcelDataSource<T> extends FileDataSource<T> {

	public ExcelDataSource(Class<?> cls, String dataName,
			Map<String, FieldMapper> mapper) {
		super(cls, dataName, mapper);
	}

	@Override
	protected List<FileInfo> initDataFiles(String filePath) {
		
		return null;
	}
	
	@Override
	protected List<String> getTitle() {
		
		return Arrays.asList("姓名","年龄");
	}

	
	@Override
	protected Object[][] readDatas(File file, int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
