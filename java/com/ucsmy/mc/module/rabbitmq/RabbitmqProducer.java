package com.ucsmy.mc.module.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ucs_mawenzhong on 2017/7/26.
 */
@Component
public class RabbitmqProducer {

    //@Autowired
    private AmqpTemplate amqpTemplate;

    public void sendQueue(String exchange_key, String queue_key, Object object) {
        amqpTemplate.convertAndSend(exchange_key, queue_key, object);
    }

}
