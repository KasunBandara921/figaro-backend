package com.figaro.salon_management_system.service;





import com.figaro.salon_management_system.model.ServiceItem;
import com.figaro.salon_management_system.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service   // tells Spring: "manage this class for me"
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<ServiceItem> getAllServices() {
        return serviceRepository.findAll();
    }

    public ServiceItem createService(ServiceItem service) {
        return serviceRepository.save(service);
    }
}