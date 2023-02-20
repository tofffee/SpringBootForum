package com.example.springforumapp.rabbitMQ;


import com.rabbitmq.client.AMQP;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final String exchange = "myrabbitmq.direct";
    private final String routingKey = "myrabbitmq.routingkey";
    private final String queue = "myrabbitmq.queue";
    @Bean
    public Queue queue() {
        return new Queue(queue, false);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

//    private static int i=0;
//    @Scheduled(fixedDelay = 5000)
//    public void produce(){
//        rabbitTemplate.convertAndSend(exchange, routingKey, String.format("counter is %d",i));
//        log.info(String.format("counter is %d",i));
//        i++;
//    }
}
