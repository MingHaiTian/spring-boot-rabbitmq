package com.example.rabbitmq.listener;

import com.alibaba.fastjson.JSON;
import com.example.rabbitmq.command.TestCommand;
import com.example.rabbitmq.configuration.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void process(Message message) {
        TestCommand command = JSON.parseObject(new String(message.getBody()), TestCommand.class);
        logger.info("接收处理队列[{}]的消息[{}]", RabbitMqConfig.QUEUE_NAME, command.toString());
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME2)
    public void process2(Message message) {
        String aa = new String(message.getBody());
        // TestCommand testCommand = (TestCommand) JSON.parse4(aa);
        // logger.info(testCommand.toString());
        Object aaa = new Jackson2JsonMessageConverter().fromMessage(message);
        // TestCommand testCommand = (TestCommand) this.convert.fromMessage(message);
        logger.info("接收处理队列[{}]的消息[{}]", RabbitMqConfig.QUEUE_NAME2, aa);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME3)
    public void process3(Message message) {
        String aa = new String(message.getBody());
        // TestCommand testCommand = (TestCommand) JSON.parse4(aa);
        // logger.info(testCommand.toString());
        Object aaa = new Jackson2JsonMessageConverter().fromMessage(message);
        // TestCommand testCommand = (TestCommand) this.convert.fromMessage(message);
        logger.info("接收处理队列[{}]的消息[{}]", RabbitMqConfig.QUEUE_NAME3, aa);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME4)
    public void process4(Message message) {
        String aa = new String(message.getBody());
        // TestCommand testCommand = (TestCommand) JSON.parse4(aa);
        // logger.info(testCommand.toString());
        Object aaa = new Jackson2JsonMessageConverter().fromMessage(message);
        // TestCommand testCommand = (TestCommand) this.convert.fromMessage(message);
        logger.info("接收处理队列[{}]的消息[{}]", RabbitMqConfig.QUEUE_NAME4, aa);
    }
}
