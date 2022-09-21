package com.registronacional.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Value("${spring.rabbitmq.queue}")
    private String queue;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.cachesize}")
    private int cachesize;
    
    
//    @Bean
//    Queue queue() {
//    	return new Queue(queue,true);
//    } 
//    @Bean
//    Exchange myExchange() {
//    	return ExchangeBuilder.directExchange(exchange).durable(true).build();
//    } 
//    @Bean
//    Binding binding() {
//        return BindingBuilder
//                .bind(queue())
//                .to(myExchange())
//                .with(routingKey)
//                .noargs();
//    }
//    @Bean
//    public ConnectionFactory connectionFactory() {
//    	    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//	        cachingConnectionFactory.setUsername(username);
//	        cachingConnectionFactory.setPassword(password);
//	        cachingConnectionFactory.setHost(host);
//	        cachingConnectionFactory.setPort(port);
//	        cachingConnectionFactory.setChannelCacheSize(cachesize);
//	        return cachingConnectionFactory;
//    }
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//        return rabbitTemplate;
//    }
    
    @Bean
	public ConnectionFactory connectionFactorySigmon() {
		CachingConnectionFactory connectionFactoryCommand = new CachingConnectionFactory();
		connectionFactoryCommand.setUsername(username);
		connectionFactoryCommand.setPassword(password);
		connectionFactoryCommand.setHost(host);
		connectionFactoryCommand.setPort(port);
		connectionFactoryCommand.setChannelCacheSize(cachesize);
		return connectionFactoryCommand;
	}
    
    @Bean
	public MessageConverter jsonMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}
    
    @Bean
	public SimpleMessageListenerContainer listenerContainer() {
		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactorySigmon());
		//listenerContainer.setMessageConverter(jsonMessageConverter());
		return listenerContainer;
	}
    

}
