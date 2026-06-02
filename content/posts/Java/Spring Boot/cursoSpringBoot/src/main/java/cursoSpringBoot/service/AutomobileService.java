package cursoSpringBoot.service;

import cursoSpringBoot.domain.Automobile;
import cursoSpringBoot.domain.AutomobileRepository;
import org.springframework.stereotype.Service;

@Service
public class AutomobileService {

    private final AutomobileRepository automobileRepository;

    public AutomobileService(AutomobileRepository automobileRepository) {
        this.automobileRepository = automobileRepository;
    }

    public Automobile createAutomobile(Automobile automobile){
        return this.automobileRepository.save(automobile);
    }
}
