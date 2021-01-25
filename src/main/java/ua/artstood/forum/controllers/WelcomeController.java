package ua.artstood.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping()
    public String welcome(){
        System.out.println("welcome");
        return "index";
    }
}
