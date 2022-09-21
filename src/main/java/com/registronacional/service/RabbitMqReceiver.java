package com.registronacional.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener; 
import org.springframework.stereotype.Component;

import com.registronacional.request.listenerrabbitmq.Legacy;

@Component
public class RabbitMqReceiver {
	private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);
	
	 
	
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void receivedMessage(Legacy legacy) {
		logger.info("Mensaje recibido: " + legacy);
	}

}
