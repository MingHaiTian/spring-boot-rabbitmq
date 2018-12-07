package com.example.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public final static String QUEUE_NAME = "tianmh-queue";
    public final static String QUEUE_NAME2 = "tianmh-queue2";
    public final static String QUEUE_NAME3 = "tianmh-queue3";
    public final static String QUEUE_NAME4 = "tianmh-queue-direct";
    public final static String EXCHANGE_NAME = "tianmh-exchange";
    public final static String EXCHANGE_NAME2 = "tianmh-exchange-direct";
    public final static String ROUTING_KEY = "#.log.#";
    public final static String ROUTING_KEY_2 = "#.log.tianmh.succ.#";
    public final static String ROUTING_KEY_3 = "#.log.tianmh.fail.#";
    public final static String ROUTING_KEY_4 = "#.test.#";

    @Autowired
    private RabbitAdmin rabbitAdmin;

    // 创建队列1
    @Bean(value = QUEUE_NAME)
    public Queue queue() {
        Queue queue = new Queue(QUEUE_NAME, true, true, true);
        this.rabbitAdmin.declareQueue(queue);
        return queue;
    }

    // 创建队列2
    @Bean(value = QUEUE_NAME2)
    public Queue queue2() {
        Queue queue = new Queue(QUEUE_NAME2, true, true, true);
        this.rabbitAdmin.declareQueue(queue);
        return queue;
    }

    // 创建队列3
    @Bean(value = QUEUE_NAME3)
    public Queue queue3() {
        Queue queue = new Queue(QUEUE_NAME3, true, true, true);
        this.rabbitAdmin.declareQueue(queue);
        return queue;
    }

    // 创建队列4
    @Bean(value = QUEUE_NAME4)
    public Queue queue4() {
        Queue queue = new Queue(QUEUE_NAME4, true, true, true);
        this.rabbitAdmin.declareQueue(queue);
        return queue;
    }

    // 创建一个 Fanout 类型的交换器
    @Bean(value = EXCHANGE_NAME)
    public Exchange exchange() {
        Exchange exchange = new TopicExchange(EXCHANGE_NAME, true, true);
        this.rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    // 创建一个 Direct 类型的交换器
    @Bean(value = EXCHANGE_NAME2)
    public Exchange exchange2() {
        Exchange exchange = new DirectExchange(EXCHANGE_NAME2, true, true);
        this.rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding binding(@Qualifier(QUEUE_NAME) Queue queue, @Qualifier(EXCHANGE_NAME) Exchange exchange) {
        Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), ROUTING_KEY, null);
        this.rabbitAdmin.declareBinding(binding);
        return binding;
    }

    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding binding2(@Qualifier(QUEUE_NAME2) Queue queue, @Qualifier(EXCHANGE_NAME) Exchange exchange) {
        Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), ROUTING_KEY_2, null);
        this.rabbitAdmin.declareBinding(binding);
        return binding;
    }

    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding binding3(@Qualifier(QUEUE_NAME3) Queue queue, @Qualifier(EXCHANGE_NAME) Exchange exchange) {
        Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), ROUTING_KEY_3, null);
        this.rabbitAdmin.declareBinding(binding);
        return binding;
    }

    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding binding4(@Qualifier(QUEUE_NAME4) Queue queue, @Qualifier(EXCHANGE_NAME2) Exchange exchange) {
        Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), ROUTING_KEY_4, null);
        this.rabbitAdmin.declareBinding(binding);
        return binding;
    }

    // 初始化连接
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1", 5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    // 队列操作配置
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


    // 发送消息模板配置
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

}
