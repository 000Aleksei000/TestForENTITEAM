package com.sergeev.supreme_ruler;

import com.sergeev.supreme_ruler.accessingdatajpa.LordRepository;
import com.sergeev.supreme_ruler.model.Lord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SupremeRulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupremeRulerApplication.class, args);
    }

}
