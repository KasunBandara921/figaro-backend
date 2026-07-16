package com.figaro.salon_management_system.controller;




import com.figaro.salon_management_system.model.ServiceItem;
import com.figaro.salon_management_system.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<ServiceItem> getAllServices() {
        return serviceService.getAllServices();
    }

    @PostMapping
    public ServiceItem createService(@RequestBody ServiceItem service) {
        return serviceService.createService(service);
    }
}