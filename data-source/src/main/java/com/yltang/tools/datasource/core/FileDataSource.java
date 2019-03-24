package com.yltang.tools.datasource.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yltang.tools.datasource.entity.FieldMapper;
import com.yltang.tools.datasource.entity.FileInfo;
import com.yltang.tools.datasource.utils.StringUtils;

public abstract class FileDataSource<T> extends AbstractDataSource<T> {
	
	/**
	 * 文件路径属性字段名
	 * */
	public final static String FILE_PATH = "file_path";
	
	protected List<FileInfo> fileInfos;

	public FileDataSource(Class<?> cls, String dataName,
			Map<String, FieldMapper> mapper) {
		super(cls, dataName, mapper);
	}
	
	@Override
	public void initDataSource(Map<String,Object> params) {
		//获取文件路径
		String filePath = params.get(FILE_PATH).toString();
		if(StringUtils.isEmpty(filePath)){
			throw new RuntimeException(String.format("请设置打开的文件或者目录，传入参数[%s]", FILE_PATH));
		}
		//初始化文件信息
		fileInfos = initDataFiles(filePath);
	};

	@Override
	public void closeDataSource() {
		fileInfos = null;
	}
	
	@Override
	protected List<List<Object>> readDatas(int startIndex, int endIndex) {
		if(fileInfos == null || fileInfos.size() <= 0){
			return null;
		}
		
		int currentTotal = 0;
		boolean findStart = false;
		List<List<Object>> cacheDatas = new ArrayList<List<Object>>();
		for(FileInfo fileInfo:fileInfos){
			currentTotal += fileInfo.getDataLine();
			if(currentTotal < startIndex){
				continue;
			}
			
			if(!findStart){
				//计算文件开始下标
				int fstart =startIndex - currentTotal + fileInfo.getDataLine();
				if(endIndex <= currentTotal){
					int fend = endIndex - currentTotal + fileInfo.getDataLine();
					List<List<Object>> rds = readDatas(fileInfo.getFile(), fstart, fend);
					cacheDatas.addAll(rds);
					break;
				}else{
					List<List<Object>> rds = readDatas(fileInfo.getFile(), fstart, fileInfo.getDataLine());
					cacheDatas.addAll(rds);
					findStart = true;
					continue;
				}
			}
			
			if(currentTotal < endIndex){
				List<List<Object>> rds = readDatas(fileInfo.getFile(), 1, fileInfo.getDataLine());
				cacheDatas.addAll(rds);
			}else{
				int fend = endIndex - currentTotal + fileInfo.getDataLine();
				List<List<Object>> rds = readDatas(fileInfo.getFile(), 1, fend);
				cacheDatas.addAll(rds);
				break;
			}
		}
		return cacheDatas;
	};
	
	protected boolean isFitFile(String fileName, List<String> fileTypes){
		if(StringUtils.isEmpty(fileName)
		|| fileTypes == null
		|| fileTypes.size() <= 0){
			return false;
		}
		
		for(String ft : fileTypes){
			if(fileName.endsWith("." + ft)){
				return true;
			}
		}
		return false;
	}
	
	protected abstract List<FileInfo> initDataFiles(String filePath); 
	
	protected abstract List<List<Object>> readDatas(File file, int startIndex, int endIndex);
}
