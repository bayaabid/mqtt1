package mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mqtt.MqttJavaApplication;
import mqtt.MqttJavaApplication.MyGateway;



@RestController
public class Controller {
	
	
	
   @Autowired
   MyGateway gateway;

	@RequestMapping("/light")
	public String light(){
		//gateway1.sendToMqtt("Patron");
		//gateway.sendToMqtt("king");
		//ser.send();
		gateway.sendToMqtt("gateway");
	//System.out.println("*******************");
		return "envoyer";
	}

	
}
