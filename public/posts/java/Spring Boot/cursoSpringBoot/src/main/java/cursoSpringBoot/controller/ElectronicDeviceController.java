package cursoSpringBoot.controller;

import cursoSpringBoot.domain.ElectronicDevices;
import cursoSpringBoot.service.ElectronicDevicesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elect-device")
public class ElectronicDeviceController {
    private final ElectronicDevicesService electronicDevicesService;

    public ElectronicDeviceController(ElectronicDevicesService electronicDevicesService) {
        this.electronicDevicesService = electronicDevicesService;
    }

    @PostMapping("/db")
    public ElectronicDevices createElectronicDevice(@RequestBody ElectronicDevices electronicDevices){
        return this.electronicDevicesService.createED(electronicDevices);
    }
}
