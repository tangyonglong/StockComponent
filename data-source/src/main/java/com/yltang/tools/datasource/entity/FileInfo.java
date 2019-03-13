package com.yltang.tools.datasource.entity;

import java.io.File;

/**
 * 文件信息
 * */
public class FileInfo {

	/**
	 * 缓存打开的文件
	 * */
	private File file;
	
	/**
	 * 缓存打开文件的数据总行数
	 * */
	private int dataLine;
	
	public FileInfo(File f, int line){
		file = f;
		dataLine = line;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getDataLine() {
		return dataLine;
	}

	public void setDataLine(int dataLine) {
		this.dataLine = dataLine;
	}
	
	
}