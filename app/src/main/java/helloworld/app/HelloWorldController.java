package helloworld.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// Main class
public class HelloWorldController {

    @RequestMapping("/hello")
    @ResponseBody
    // Method
    public String sayHello()
    {
        return "Bonjour! Nous utiliserons cette application pour notre TP de déploiement.";
    }
}