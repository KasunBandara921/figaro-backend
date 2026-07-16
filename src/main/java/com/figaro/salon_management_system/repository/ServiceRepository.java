package com.figaro.salon_management_system.repository;



import com.figaro.salon_management_system.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceItem, Long> {
    // That's it. You already get save(), findAll(), findById(), deleteById() for free.
}