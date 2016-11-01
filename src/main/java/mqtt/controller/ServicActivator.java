package mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
//@MessageEndpoint
public class ServicActivator{
	DefaultMqttPahoClientFactory factory;
	/*@Autowired
	MyGateway gateway;*/
	public ServicActivator() {
		// TODO Auto-generated constructor stub
		factory = new DefaultMqttPahoClientFactory();
	    factory.setServerURIs("tcp://localhost:1883");
	}
	
    
	
	/*@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public void send(){
		System.out.println("HHHHHHHHHHHHHHHHH");
		//gateway.sendToMqtt("baya");
	}*/
	@ServiceActivator(inputChannel = "mqttOutboundChannel",outputChannel="out")
    public String mqttOutbound(Message<String> message) {
    	/*Message<String> message1 = new Message<String>() {
			
			@Override
			public String getPayload() {
				// TODO Auto-generated method stub
				return "kais";
			}
			
			@Override
			public MessageHeaders getHeaders() {
				// TODO Auto-generated method stub
				return null;
			}
		};*/
		
		System.out.println("message"+message.getPayload());
    /*  MqttPahoMessageHandler messageHandler =
              new MqttPahoMessageHandler("java", factory);
      messageHandler.setAsync(true);
      messageHandler.setDefaultTopic("test1");
      messageHandler.setDefaultQos(2);*/
      //messageHandler.handleMessage(message);
     //return messageHandler;
      return "kaisssssssssssssssss";
    }
    
	
}
