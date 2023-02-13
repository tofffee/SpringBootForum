package com.example.springforumapp.rabbitMQ;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Producer {

    private final String exchange = "myrabbitmq.direct";
    private final String routing = "myrabbitmq.routing";
    private final String queue = "myrabbitmq.queue";

}
