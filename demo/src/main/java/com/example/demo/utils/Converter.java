package com.example.demo.utils;

import java.beans.FeatureDescriptor;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public final class Converter {
	
	public static <T> T convert (Object source, T target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
		return target;
	}
	
	public static <T> T convert (Object source, Supplier<T> supplier) {
		T target = supplier.get();
		return convert(source, target);
	}
	
	private static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
	    return Stream.of(wrappedSource.getPropertyDescriptors())
	            .map(FeatureDescriptor::getName)
	            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
	            .toArray(String[]::new);
	}
}
