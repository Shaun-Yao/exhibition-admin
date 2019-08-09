package com.honji.exhibition.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.honji.exhibition.admin.mapper")
public class ExhibitionAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExhibitionAdminApplication.class, args);
    }

}
