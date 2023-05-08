package com.himanshu.kafkabinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class KafkaBinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaBinderApplication.class, args);
    }

    //Producer will send data to topic
    @Bean
    public Supplier<String> producerBinding() {
        return () -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "new data";
        };
    }
//
    // Processor will fetch data from one topic perform its logic and then send new/modified data to other topic.
    @Bean
    public Function<String, String> processorBinding() {
        return s -> s + " :: " + System.currentTimeMillis();
    }
//      Consumer will fetch data from topic
    @Bean
    public Consumer<String> consumerBinding() {
        return s -> System.out.println("Data Consumed :: " + s.toUpperCase());
    }
}
