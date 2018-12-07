package com.example.rabbitmq.sender;

import com.alibaba.fastjson.JSONObject;
import com.example.rabbitmq.command.TestCommand;
import com.example.rabbitmq.configuration.RabbitMqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @Description:
 * @Author:tianminghai
 * @Date:4:22 PM 2018/12/7
 */
@Component
public class SenderDemo {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void testSender() {
        TestCommand command = new TestCommand();
        command.setKey("testContent");
        byte[] content = JSONObject.toJSONBytes(command);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setTimestamp(new Date());
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message message = new Message(content, messageProperties);
        rabbitTemplate.send(RabbitMqConfig.EXCHANGE_NAME, "log", message);
    }
}
