package com.himanshu.configserverclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Make sure to run config-server service first before starting client as it will fetch configuration value.
 */
@SpringBootApplication
public class ConfigServerClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerClientApplication.class, args);
    }

}
