package cursoSpringBoot.service;


import cursoSpringBoot.domain.ElectronicDevices;
import cursoSpringBoot.domain.ElectronicDevicesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ElectronicDevicesService {

    private final ElectronicDevicesRepository electronicDevicesRepository;

    public ElectronicDevicesService(ElectronicDevicesRepository electronicDevicesRepository) {
        this.electronicDevicesRepository = electronicDevicesRepository;
    }

    public ElectronicDevices createED(ElectronicDevices electronicDevices){
        return this.electronicDevicesRepository.save(electronicDevices);
    }
}
