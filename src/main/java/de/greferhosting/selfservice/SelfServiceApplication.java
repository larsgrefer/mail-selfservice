package de.greferhosting.selfservice;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Join(path = "/", to = "/index.xhtml")
@SpringBootApplication
public class SelfServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfServiceApplication.class, args);
    }

}
