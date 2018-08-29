package org.hornetq.reflection;

import java.lang.annotation.Annotation;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

public class MessageDrivenReflection implements MessageDriven  {
	
	private String name;
	private Class messageListenerInterface;
	private ActivationConfigProperty[] activationConfig;
	private String mappedName;
	private String description;
	
	public MessageDrivenReflection(String name, ActivationConfigProperty[] activationConfig) {
		this.name = name;
		this.activationConfig = activationConfig;
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		return MessageDrivenReflection.class;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Class messageListenerInterface() {
		return messageListenerInterface;
	}

	@Override
	public ActivationConfigProperty[] activationConfig() {
		return activationConfig;
	}

	@Override
	public String mappedName() {
		return mappedName;
	}

	@Override
	public String description() {
		return description;
	}

}
