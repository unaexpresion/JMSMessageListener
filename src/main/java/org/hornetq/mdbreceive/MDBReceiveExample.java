package org.hornetq.mdbreceive;

//import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.hornetq.reflection.ActivationConfigPropertyReflection;
import org.hornetq.reflection.AnnotationReflectionHelper;
import org.hornetq.reflection.MessageDrivenReflection;
 

@MessageDriven(mappedName = "java:jboss/queue/MDBReceiveExample", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/queue/myQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "100")} )
public class MDBReceiveExample implements MessageListener {
	
	{
		System.out.println("******************+ BLOQUE");
		changeMaxSession();
	}
	
	public MDBReceiveExample() {
		System.out.println("******************+ CONSTRUCTOR");
		changeMaxSession();
	}
	
	public void changeMaxSession() {
		MessageDriven driven = this.getClass().getAnnotation(MessageDriven.class);
		//System.out.println("*******" + driven.activationConfig()[3].propertyName());
		//System.out.println("*******" + driven.activationConfig()[3].propertyValue());
		
		ActivationConfigPropertyReflection activationCfgArr[] = {
				new ActivationConfigPropertyReflection("acknowledgeMode", "Auto-acknowledge"),
				new ActivationConfigPropertyReflection("destination", "java:jboss/queue/myQueue"),
				new ActivationConfigPropertyReflection("destinationType", "javax.jms.Queue"),
				new ActivationConfigPropertyReflection("maxSession", "1000")
		};
		
		MessageDrivenReflection messageDrivenReflection  = new MessageDrivenReflection(
				"java:jboss/queue/MDBReceiveExample", activationCfgArr);
		
		AnnotationReflectionHelper.alterAnnotationOn(this.getClass(), MessageDriven.class, messageDrivenReflection);
		
		driven = this.getClass().getAnnotation(MessageDriven.class);
		//System.out.println("*******" + driven.activationConfig()[3].propertyName());
		System.out.println("*******" + driven.activationConfig()[3].propertyValue());
	}
	
   public void onMessage(Message message) {
	   System.out.println("******************+ ONMESSAGE");
      try
      {
    	  changeMaxSession();
         //Step 11. We know the client is sending a text message so we cast
         TextMessage textMessage = (TextMessage)message;
 
         //Step 12. get the text from the message.
         String text = textMessage.getText();
 
         //Step 13. We check we received the right color of message
         String color = textMessage.getStringProperty("color");
 
         System.out.println("message " + text + " received color=" + color);
         Thread.sleep(3000);
 
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}