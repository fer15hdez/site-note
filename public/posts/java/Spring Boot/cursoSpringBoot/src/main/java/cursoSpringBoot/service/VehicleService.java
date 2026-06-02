package cursoSpringBoot.service;

import cursoSpringBoot.domain.Vehicle;
import cursoSpringBoot.domain.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(Vehicle vehicle){
        return this.vehicleRepository.save(vehicle);
    }
}
