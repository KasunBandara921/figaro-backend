package com.figaro.salon_management_system.repository;



import com.figaro.salon_management_system.model.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StylistRepository extends JpaRepository<Stylist, Long> {
}
