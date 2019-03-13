package com.yltang.tools.datasource.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.yltang.tools.datasource.entity.ClassAnnotationInfo;

public final class ReflectUtils {
	
	private ReflectUtils(){}
	
	/**
	 * 反射类的注解信息
	 * */
	public static ClassAnnotationInfo reflectClassAnnoInfo(Class<?> cls){
		
		//获取类的注解信息
		Map<String, Annotation[]> clsAnnoInfos = newHashMap();
		clsAnnoInfos.put(cls.getSimpleName(), cls.getAnnotations());
		
		//获取字段注解信息
		Map<String, Annotation[]> fieldAnnoInfos = newHashMap();
		for(Field field:cls.getDeclaredFields()){
			fieldAnnoInfos.put(field.getName(), field.getAnnotations());
		}
		
		//获取方法注解信息
		Map<String, Annotation[]> methodAnnoInfos = newHashMap();
		for(Method method:cls.getDeclaredMethods()){
			methodAnnoInfos.put(method.getName(), method.getAnnotations());
		}
		
		return new ClassAnnotationInfo(clsAnnoInfos, fieldAnnoInfos, methodAnnoInfos);
	}
	
	public static <k,v> HashMap<k,v> newHashMap(){
		return new HashMap<k,v>();
	}
	
	public static <E> ArrayList<E> newArrayList(){
		return new ArrayList<E>();
	}

}
