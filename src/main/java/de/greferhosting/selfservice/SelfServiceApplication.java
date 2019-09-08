package de.greferhosting.selfservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class SelfServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfServiceApplication.class, args);
    }

    @GetMapping("/")
    public String welcomePage() {
        return "redirect:/index.xhtml";
    }
}
