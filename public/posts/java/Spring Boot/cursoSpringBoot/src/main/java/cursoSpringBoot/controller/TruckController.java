package cursoSpringBoot.controller;

import cursoSpringBoot.domain.Truck;
import cursoSpringBoot.service.TruckService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/truck")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @PostMapping("/db")
    public Truck createTruck(@RequestBody Truck truck){
        return this.truckService.createTruck(truck);
    }
}
