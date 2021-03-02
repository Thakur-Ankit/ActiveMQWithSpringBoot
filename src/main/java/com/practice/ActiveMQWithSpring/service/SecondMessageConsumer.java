package com.practice.ActiveMQWithSpring.service;

import com.sun.istack.internal.logging.Logger;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class SecondMessageConsumer implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(SecondMessageConsumer.class);

    @Override
    @JmsListener(destination = "${active-mq.message.topic}")
    public void onMessage(Message message) {
        try {
            ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
            LOGGER.info("Received AMQTM By Second Consumer : " + activeMQTextMessage);
            LOGGER.info("Received By Second Consumer : " + activeMQTextMessage.getText());

        } catch (Exception e) {
            LOGGER.info("Received Exception : " + e);
        }
    }
}
