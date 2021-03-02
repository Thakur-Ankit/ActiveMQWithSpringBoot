package com.practice.ActiveMQWithSpring.service;

import com.practice.ActiveMQWithSpring.model.User;
import com.sun.istack.internal.logging.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
public class FirstObjectConsumer implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(FirstObjectConsumer.class);

    @Override
    @JmsListener(destination = "${active-mq.object.topic}")
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            User user = (User) objectMessage.getObject();
            LOGGER.info("Received Message By First Consumer: " + user.toString());

        } catch (Exception e) {
            LOGGER.info("Received Exception : " + e);
        }
    }
}
