// src/main/java/com/example/manageserver/ManageServerApplication.java
package com.example.manageserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.manageserver.dao")
public class ManageserverApplication {
    static {
        System.setProperty("org.apache.tomcat.util.compat.JreCompat.ALLOW_INACCESSIBLE_OBJECT_EXCEPTION", "true");
    }

    public static void main(String[] args) {
        SpringApplication.run(ManageserverApplication.class, args);
    }
}