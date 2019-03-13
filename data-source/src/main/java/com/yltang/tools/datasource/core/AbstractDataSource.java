package com.yltang.tools.datasource.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yltang.tools.datasource.DataSource;
import com.yltang.tools.datasource.entity.FieldMapper;

public abstract class  AbstractDataSource<T> implements DataSource<T>{

	/**
	 * 设置数据类型
	 * */
	protected Class<?> cls;
	
	/**
	 * 数据名称
	 * */
	protected String dataName;
	
	/**
	 * 数据字段映射
	 * */
	protected Map<String, FieldMapper> fieldMapping;
	
	public AbstractDataSource(Class<?> cls,String dataName, Map<String, FieldMapper> mapper){
		this.cls = cls;
		this.dataName = dataName;
		this.fieldMapping = mapper;
	}
	
	/**
	 * 初始化数据
	 * */
	protected abstract void initDataSource(Map<String, Object> params);
	
	/**
	 * 打开数据资源
	 * */
	@Override
	public void openDataSource(Map<String, Object> params) {
		
		//初始化数据资源
		initDataSource(params);
		
		//设置标题
		setTitle();
	}

	/**
	 * 读取数据
	 * */
	@Override
	public List<T> readData(int page,int size) throws IllegalAccessException, Exception {
		//设置下标
		int startIndex = getStartIndex(page, size);
		int endIndex = getEndIndex(page, size);
		
		//读取原始数据
		Object[][] datas = readDatas(startIndex, endIndex);
		List<T> dataList = new ArrayList<T>();
		//填充设置的数据类型列表
		fillDataList(datas, dataList);
		return dataList;
	}

	/**
	 * 获取标题
	 * */
	protected abstract List<String> getTitle();
	
	/**
	 * 读取原始数据
	 * */
	protected abstract Object[][] readDatas(int startIndex, int endIndex);
	
	/**
	 * 设置标题字段映射
	 * */
	private void setTitle(){
		List<String> titles = getTitle();
		if(titles != null && titles.size() > 0){
			for(int i=0; i < titles.size(); i++ ){
				String title = titles.get(i);
				if(fieldMapping.containsKey(title)){
					fieldMapping.get(title).setIndex(i);
				}
			}
		}
		
	} 
	
	/**
	 * 填充数据列表
	 * */
	private void fillDataList(Object[][] datas, List<T> dataList) throws IllegalAccessException, Exception{
		if(datas != null && datas.length > 0){
			for(Object[] row : datas){
				T result = fillData(row);
				if(result != null){
					dataList.add(result);
				}
			}
		}
	}
	
	/**
	 * 填充单个对象
	 * */
	private T fillData(Object[] data) throws Exception, IllegalAccessException{
		@SuppressWarnings("unchecked")
		T entity = (T)cls.newInstance();
		setObj(entity, data);
		return entity;
		
	}
	
	/**
	 * 设置单个对象
	 * */
	private void setObj(T obj,Object[] data) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		for(Map.Entry<String, FieldMapper> enty:fieldMapping.entrySet()){
			FieldMapper fieldMapgger = enty.getValue();
			int index =  fieldMapgger.getIndex();
			if(index != -1){
				Object ov = fieldMapgger.getFieldType().parse(data[index]);
				setValue(obj, enty.getValue().getFieldName(),ov);
			}
		}
	}
	
	/**
	 * 设置单个值
	 * */
	private void setValue(T obj, String k, Object v) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field f = obj.getClass().getDeclaredField(k);
		f.setAccessible(true);
		f.set(obj, v);
	}
	
	/**
	 * 获取当前页开始下标
	 * */
	private int getStartIndex(int page, int size){
		if(page <= 0 || size <= 0){
			return 0;
		}
		
		return (page - 1) * size + 1;
	}
	
	/**
	 * 获取当前页结束下标
	 * */
	private int getEndIndex(int page, int size){
		if(page <= 0 || size <= 0){
			return 0;
		}
		
		return page * size;
	}

}
