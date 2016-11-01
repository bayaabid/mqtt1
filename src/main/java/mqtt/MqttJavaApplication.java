package mqtt;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;



@SpringBootApplication
@IntegrationComponentScan
public class MqttJavaApplication {

    public static void main(String[] args) {
       /* ConfigurableApplicationContext context =
        		new SpringApplicationBuilder(MqttJavaApplication.class)
        				.web(false)
        				.run(args);*/
    	ConfigurableApplicationContext context =
    			SpringApplication.run(MqttJavaApplication.class, args);
       /* MyGateway gateway = context.getBean(MyGateway.class);
        gateway.sendToMqtt("foochaabane");
        gateway.sendToMqtt("foochaabane");
        MyGateway1 gateway1 = context.getBean(MyGateway1.class);
        gateway1.sendToMqtt("foo1kais");
        gateway1.sendToMqtt("foo1kais");*/
    }

    /*@Bean
    public Executor customExecutor(){
    	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    	executor.setCorePoolSize(10);
    	return executor;
    }*/
    
   @Bean
     public MessageChannel mqttInputChannel() {
      return new DirectChannel();
    }
   
   /*
    @Bean
    public MessageChannel mqttInputChannel1() {
      return new DirectChannel();
    }*/
/*
    @Bean
    public MessageChannel mqttInputChannel() {
      return new PublishSubscribeChannel();
    }*/
    
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
      DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
      factory.setServerURIs("tcp://localhost:1883");
      
      return factory;
    }

    @Bean
    public MessageProducer inbound() {
    	
      MqttPahoMessageDrivenChannelAdapter adapter =
              new MqttPahoMessageDrivenChannelAdapter("java", mqttClientFactory(),
                      "test","test1");
      adapter.setCompletionTimeout(10000);
      adapter.setConverter(new DefaultPahoMessageConverter());
      adapter.setQos(1);
      adapter.setOutputChannel(mqttInputChannel());
      
   
      return adapter;
    }
    

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
      return new MessageHandler() {

        @Override
        public void handleMessage(Message<?> message) throws MessagingException {
          System.out.println("!!!!!!!!!!!!!!!!!!!" + message.getPayload());
          
        }

      };
    }
    
    
   /* @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
    	
      MqttPahoMessageHandler messageHandler =
              new MqttPahoMessageHandler("java", mqttClientFactory());
      messageHandler.setAsync(true);
      messageHandler.setDefaultTopic("test1");
      messageHandler.setDefaultQos(2);
      return messageHandler;
    }*/
    
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel1")
    public MessageHandler mqttOutbound1() {
      MqttPahoMessageHandler messageHandler =
              new MqttPahoMessageHandler("java", mqttClientFactory());
      messageHandler.setAsync(true);
      messageHandler.setDefaultTopic("test");
      messageHandler.setDefaultQos(2);
      return messageHandler;
    }
    
    @Bean
    @ServiceActivator(inputChannel = "out")
    public MessageHandler mqttOut() {
      MqttPahoMessageHandler messageHandler =
              new MqttPahoMessageHandler("java", mqttClientFactory());
      messageHandler.setAsync(true);
      messageHandler.setDefaultTopic("test");
      messageHandler.setDefaultQos(2);
      return messageHandler;
    }
    
    @Bean
    public MessageChannel publishsubscribechannel() {

    return new PublishSubscribeChannel(executor());
    } 

    @Bean
    public ThreadPoolTaskExecutor executor() {

    ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
    pool.setCorePoolSize(10);
    pool.setMaxPoolSize(10);
    pool.setWaitForTasksToCompleteOnShutdown(true);
    return pool;
    }
    @Bean
    public MessageChannel mqttOutboundChannel1() {
      return new PublishSubscribeChannel();
    }
    
    @Bean
    public MessageChannel mqttOutboundChannel() {
      return new DirectChannel();
    }
    @Bean
    public MessageChannel out() {
      return new DirectChannel();
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MyGateway {

      void sendToMqtt(String data);
     
    }
    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    //@Async("customExecutor")
    public interface MyGateway1 {
    //@Async("customExecutor")
     void sendToMqtt(String data);

    }
    @Bean
    public MessageChannel mqtt() {
      return new PublishSubscribeChannel();
    }
    
    @Bean
    @ServiceActivator(inputChannel = "mqtt")
    public MessageHandler outbountSer() {
    	
    	MqttPahoMessageHandler messageHandler =
              new MqttPahoMessageHandler("java", mqttClientFactory());
    	   messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("test");
        messageHandler.setDefaultQos(2);
		System.out.println("HHHHHHHHHHHHHHHHH");
		//messageHandler.handleMessage(message);
        PublishSubscribeChannel psc = (PublishSubscribeChannel) mqtt();
        psc.subscribe(messageHandler);
        
        return messageHandler;
    }

  }
