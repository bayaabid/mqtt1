package mqtt.controller;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MyGateway {
	

    void sendToMqtt(Message<String>message);


}
