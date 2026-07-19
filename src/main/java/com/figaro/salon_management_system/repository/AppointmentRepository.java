
package com.figaro.salon_management_system.repository;
import com.figaro.salon_management_system.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomerEmail(String email);
}