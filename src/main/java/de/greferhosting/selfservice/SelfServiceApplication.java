package de.greferhosting.selfservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@Controller
public class SelfServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfServiceApplication.class, args);
    }

    @RequestMapping("/")
    public RedirectView indexRedirect() {
        return new RedirectView("/index.xhtml");
    }
}
