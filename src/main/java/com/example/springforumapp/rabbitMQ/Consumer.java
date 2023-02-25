package com.example.springforumapp.rabbitMQ;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class Consumer {
//
//    @RabbitListener(queues = "myrabbitmq.queue")
//    public void consume(String msg) {
//        log.info(String.format("Consume : %s",msg));
//    }
//}
