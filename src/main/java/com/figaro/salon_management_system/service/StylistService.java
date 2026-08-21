package com.figaro.salon_management_system.service;



import com.figaro.salon_management_system.model.Stylist;
import com.figaro.salon_management_system.model.Appointment;
import com.figaro.salon_management_system.repository.StylistRepository;
import com.figaro.salon_management_system.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StylistService {

    @Autowired
    private StylistRepository stylistRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Stylist> getAllStylists() {
        return stylistRepository.findAll();
    }

    public Stylist createStylist(Stylist stylist) {
        return stylistRepository.save(stylist);
    }

    public Stylist updateStylist(Long id, Stylist updated) {
        Stylist existing = stylistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stylist not found"));
        existing.setName(updated.getName());
        existing.setRating(updated.getRating());
        existing.setSpecialties(updated.getSpecialties());
        return stylistRepository.save(existing);
    }

    public void deleteStylist(Long id) {
        Stylist stylist = stylistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stylist not found"));
        List<Appointment> appointments = appointmentRepository.findByStylistId(id);
        for (Appointment app : appointments) {
            app.setStylist(null);
            appointmentRepository.save(app);
        }
        stylistRepository.delete(stylist);
    }
}