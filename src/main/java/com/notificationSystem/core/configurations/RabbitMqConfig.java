package com.notificationSystem.core.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    
    public static final String EXCHANGE = "notifications-exchange";
    public static final String QUEUE_BURSA = "queue-Bursa";
    public static final String QUEUE_ISTANBUL = "queue-Istanbul";

    @Bean
    public FanoutExchange  fanoutExchange() {
        return new FanoutExchange(EXCHANGE);
    }
    
    @Bean
    public Queue queueBursa() {
        return new Queue(QUEUE_BURSA, false);
    }

    @Bean
    public Queue queueIstanbul() {
        return new Queue(QUEUE_ISTANBUL, false);
    }

    @Bean
    public Binding bindingBursa(@Qualifier("queueBursa") Queue queueBursa, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueBursa).to(fanoutExchange);
    }
    @Bean
    public Binding bindingIstanbul(@Qualifier("queueIstanbul") Queue queueIstanbul, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueIstanbul).to(fanoutExchange);
    }
    


}
