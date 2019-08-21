/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.zbl.code.common.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

/**
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * 
 * 1. 持有Mapper的单例.
 * 2. 返回值类型转换.
 * 3. 批量转换Collection中的所有对象.
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * 
 * @author calvin
 */
public class BeanMapper {

	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
	 */
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	/**
	 * 基于Dozer转换对象的类型.
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.
	 * TODO 过滤 四个条件 修改时间、创建时间、修改人，修改者编号
	 */
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.
	 * TODO 过滤 四个条件 修改时间、创建时间、修改人，修改者编号
	 */
	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}
	
	/**
	 * 基于Apache将对象A的值拷贝到对象B中.
	 * TODO 过滤 四个条件 修改时间、创建时间、修改人，修改者编号
	 */
	public static void copyByApache(Object source, Object destinationObject) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(destinationObject, source);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 基于Spring将对象A的值拷贝到对象B中.
	 * TODO 过滤 四个条件 修改时间、创建时间、修改人，修改者编号
	 */
	public static void copyBySpring(Object source, Object destinationObject) {
		org.springframework.beans.BeanUtils.copyProperties(destinationObject, source);
	}
}