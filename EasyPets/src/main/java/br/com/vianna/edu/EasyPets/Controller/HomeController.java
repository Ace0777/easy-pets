package br.com.vianna.edu.EasyPets.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String login() {
        return "login";
    }
    @GetMapping("/login")
    public String login2() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/negado")
    public String negado() {
        return "negado";
    }
}
