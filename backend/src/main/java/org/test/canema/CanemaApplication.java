package org.test.canema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CanemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanemaApplication.class, args);
    }

}
