package com.yltang.tools.datasource.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.yltang.tools.datasource.entity.FieldMapper;
import com.yltang.tools.datasource.entity.FileInfo;
import com.yltang.tools.datasource.utils.FileUtils;

public class ExcelDataSource<T> extends FileDataSource<T> {
	
	//定义支持读取的文件类型
	private static final List<String> fileTypes = Arrays.asList("xls","xlsx"); 

	public ExcelDataSource(Class<?> cls, String dataName,
			Map<String, FieldMapper> mapper) {
		super(cls, dataName, mapper);
	}

	/**
	 * 传入的是目录会自动搜索当前目录下的文件，不对目录下的目录做递归
	 * */
	@Override
	protected List<FileInfo> initDataFiles(String filePath) {
		List<FileInfo> fInfos = new ArrayList<FileInfo>();
		File file = new File(filePath);
		if(file.isFile()){
			if(isFitFile(filePath, fileTypes)){
				int line = FileUtils.getExcelRowNum(file);
				fInfos.add(new FileInfo(file, line));
			}
		}else if(file.isDirectory()){
			String[] files = file.list();
			for(String f:files){
				file = new File(filePath + "/" +f);
				if(file.isDirectory()){
					continue;
				}
				if(isFitFile(f, fileTypes)){
					int line = FileUtils.getExcelRowNum(file);
					fInfos.add(new FileInfo(file, line));
				}
			}
		}
		return fInfos;
	}
	
	@Override
	protected List<String> getTitle() {
		List<String> title = new ArrayList<String>();
		if(fileInfos != null && fileInfos.size() > 0){
			List<List<Object>> datas = FileUtils.readExcelTitle(fileInfos.get(0).getFile());
			if(datas != null && datas.size() > 0){
				List<Object> objs = datas.get(0);
				if(objs == null || objs.size() <= 0){
					return title;
				}
				for(Object obj : objs){
					title.add(String.valueOf(obj));
				}
			}
		}
		return title;
	}

	
	@Override
	protected List<List<Object>> readDatas(File file, int startIndex, int endIndex) {
		List<List<Object>> datas = FileUtils.readExcel(file, startIndex, endIndex);
		return datas;
	}
}
