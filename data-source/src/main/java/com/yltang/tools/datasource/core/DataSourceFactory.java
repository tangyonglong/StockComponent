package com.yltang.tools.datasource.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.yltang.tools.datasource.DataSource;
import com.yltang.tools.datasource.annotation.DataEntity;
import com.yltang.tools.datasource.annotation.DataOrigin;
import com.yltang.tools.datasource.annotation.FieldMapping;
import com.yltang.tools.datasource.entity.ClassAnnotationInfo;
import com.yltang.tools.datasource.entity.DataOriginType;
import com.yltang.tools.datasource.entity.FieldMapper;
import com.yltang.tools.datasource.utils.ReflectUtils;

public class DataSourceFactory {

	private DataSourceFactory(){};
	
	@SuppressWarnings("unchecked")
	public static <T> DataSource<T> buildDataSource(Class<T> cls) throws Exception{
		ClassAnnotationInfo info = ReflectUtils.reflectClassAnnoInfo(cls);

		//解析类注解
		String dataSourceName = "";
		DataOriginType type = null;
		if(info.getClassAnnotations() != null){
			for(Annotation anno:info.getClassAnnotations().get(cls.getSimpleName())){
				if(anno instanceof DataEntity){
					DataEntity de = (DataEntity)anno;
					dataSourceName = de.value();
				}
				
				if(anno instanceof DataOrigin){
					DataOrigin dg = (DataOrigin)anno;
					type = dg.value();
				}
			}
		}
		if("".equals(dataSourceName.trim()) || type == null){
			throw new RuntimeException("缺少类型定义或者数据标识");
		}
		
		//解析数据关系映射
		Map<String, FieldMapper> fieldMappters = new HashMap<String, FieldMapper>();
		if(info.getFieldAnnotations() != null){
			for(Map.Entry<String, Annotation[]> enty:info.getFieldAnnotations().entrySet()){
				String key = enty.getKey();
				Annotation[] annos = enty.getValue();
				for(Annotation anno:annos){
					if(anno instanceof FieldMapping){
						FieldMapping fm = (FieldMapping)anno;
						fieldMappters.put(fm.sourceName(), new FieldMapper(fm.sourceName(), key, fm.type()));
					}
				}
			}
		}
		
		//创建DataSource类型对象
		Class<?> c = Class.forName(type.getVal());
		@SuppressWarnings("rawtypes")
		Class[] parameterTypes = {Class.class, String.class, Map.class};
		@SuppressWarnings("rawtypes")
		Constructor constructor = c.getConstructor(parameterTypes);
		Object[] p = {cls, dataSourceName, fieldMappters};
		Object o = constructor.newInstance(p);
		return (DataSource<T>)o;
	
	}
	
}
