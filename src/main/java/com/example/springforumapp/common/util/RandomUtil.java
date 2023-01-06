package com.example.springforumapp.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomUtil {
    private final Random random;

    @Autowired
    public RandomUtil(Random random) {
        this.random = random;
    }

    public String generateCode(){
        return String.valueOf(random.nextInt(9000) + 1000);
    }
}
