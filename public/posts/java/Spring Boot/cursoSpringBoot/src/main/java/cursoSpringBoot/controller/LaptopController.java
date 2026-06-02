package cursoSpringBoot.controller;

import cursoSpringBoot.domain.ElectronicDevices;
import cursoSpringBoot.domain.Laptop;
import cursoSpringBoot.service.ElectronicDevicesService;
import cursoSpringBoot.service.LaptopService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elect-device/laptop")
public class LaptopController {
    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/db")
    public Laptop createlaptop(@RequestBody Laptop laptop){
        return this.laptopService.createLaptop(laptop);
    }
}
