package com.simplefaas.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleFaaSRegistryApplication {

    public static void main(String[] args) {

        SpringApplication.run(SimpleFaaSRegistryApplication.class, args);




        // 启动定期删除过期server线程

    }

}
