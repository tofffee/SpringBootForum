package com.example.springforumapp.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class RandomUtil {
    private final Random random;

    public String generateCode(){
        return String.valueOf(random.nextInt(9000) + 1000);
    }
}
