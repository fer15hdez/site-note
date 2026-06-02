package cursoSpringBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @GetMapping("/hello")
    public String helloWorld(){
        System.out.println("esta en ejecucion");
        return "Hello world";
    }
}
