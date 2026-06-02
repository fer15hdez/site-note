package cursoSpringBoot.controller;

import cursoSpringBoot.domain.Automobile;
import cursoSpringBoot.service.AutomobileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/automobile")
public class AutomobileController {

    private final AutomobileService automobileService;


    public AutomobileController(AutomobileService automobileService) {
        this.automobileService = automobileService;
    }

    @PostMapping("/db")
    public Automobile createAutomobile(@RequestBody Automobile automobile){
        return this.automobileService.createAutomobile(automobile);
    }
}
