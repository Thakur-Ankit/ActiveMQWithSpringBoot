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

    @Value("${active-mq.message.topic}")
    private String messageTopic;

    @Value("${active-mq.object.topic}")
    private String objectTopic;

    public boolean sendMessage(String message) {
        try {
            LOGGER.info("Attempting Send message to Topic: " + messageTopic);
            jmsTemplate.convertAndSend(messageTopic, message);
        } catch (Exception e) {
            LOGGER.info("Recieved Exception during send Message: " + e);
            return false;
        }
        return true;
    }

    public boolean sendUser(User userObj) {
        try {
            LOGGER.info("Attempting Send User Object to Topic: " + objectTopic);
            jmsTemplate.convertAndSend(objectTopic, userObj);
        } catch (Exception e) {
            LOGGER.info("Recieved Exception during send user object: " + e);
            return false;
        }
        return true;
    }
}
