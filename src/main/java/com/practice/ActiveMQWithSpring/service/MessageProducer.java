package com.practice.ActiveMQWithSpring.service;

import com.practice.ActiveMQWithSpring.model.User;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class MessageProducer {

    private static final Logger LOGGER = Logger.getLogger(MessageProducer.class);

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${active-mq.topic}")
    private String topic;

    public boolean sendMessage(String message) {
        try {
            LOGGER.info("Attempting Send message to Topic: " + topic);
            jmsTemplate.convertAndSend(topic, message);
        } catch (Exception e) {
            LOGGER.info("Recieved Exception during send Message: " + e);
            return false;
        }
        return true;
    }

    public boolean sendUser(User userObj) {
        try {
            LOGGER.info("Attempting Send User Object to Topic: " + topic);
            jmsTemplate.convertAndSend(topic, userObj);
        } catch (Exception e) {
            LOGGER.info("Recieved Exception during send user object: " + e);
            return false;
        }
        return true;
    }
}
