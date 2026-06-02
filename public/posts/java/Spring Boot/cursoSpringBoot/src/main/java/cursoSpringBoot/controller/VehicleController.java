package cursoSpringBoot.controller;

import cursoSpringBoot.domain.Vehicle;
import cursoSpringBoot.service.VehicleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/db")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){

        return this.vehicleService.createVehicle(vehicle);
    }
}
