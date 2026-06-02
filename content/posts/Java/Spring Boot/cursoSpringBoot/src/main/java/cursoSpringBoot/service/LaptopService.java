package cursoSpringBoot.service;


import cursoSpringBoot.domain.Laptop;
import cursoSpringBoot.domain.LaptopRepository;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public Laptop createLaptop(Laptop laptop){
        return this.laptopRepository.save(laptop);
    }
}
