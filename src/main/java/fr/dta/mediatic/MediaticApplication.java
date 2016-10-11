package fr.dta.mediatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("fr.dta.mediatic")
public class MediaticApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaticApplication.class, args);
    }

}
