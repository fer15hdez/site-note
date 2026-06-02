package cursoSpringBoot.service;

import cursoSpringBoot.domain.Truck;
import cursoSpringBoot.domain.TruckRepository;
import org.springframework.stereotype.Service;

@Service
public class TruckService {

    private final TruckRepository truckRepository;


    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public Truck createTruck(Truck truck){
        return this.truckRepository.save(truck);
    }
}
