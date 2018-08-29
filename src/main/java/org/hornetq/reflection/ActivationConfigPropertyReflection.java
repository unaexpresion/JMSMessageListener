package org.hornetq.reflection;

import java.lang.annotation.Annotation;

import javax.ejb.ActivationConfigProperty;

public class ActivationConfigPropertyReflection implements ActivationConfigProperty {
	
	private String propertyName;
	private String propertyValue;
	
	public ActivationConfigPropertyReflection(String propertyName, String propertyValue) {
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		return ActivationConfigPropertyReflection.class;
	}

	@Override
	public String propertyName() {
		return propertyName;
	}

	@Override
	public String propertyValue() {
		return propertyValue;
	}

}
