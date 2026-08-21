package com.figaro.salon_management_system.service;

import com.figaro.salon_management_system.model.Appointment;
import com.figaro.salon_management_system.model.Stylist;
import com.figaro.salon_management_system.repository.AppointmentRepository;
import com.figaro.salon_management_system.repository.StylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private StylistRepository stylistRepository;

    public Appointment createAppointment(Appointment appointment) {
        appointment.setStatus("PENDING");
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByCustomer(String email) {
        return appointmentRepository.findByCustomerEmail(email);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment updateAppointmentStatus(Long id, String status) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        existing.setStatus(status);
        return appointmentRepository.save(existing);
    }

    public Appointment assignStylist(Long id, Long stylistId) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Stylist stylist = stylistRepository.findById(stylistId)
                .orElseThrow(() -> new RuntimeException("Stylist not found"));
        existing.setStylist(stylist);
        return appointmentRepository.save(existing);
    }

    public List<Appointment> getAppointmentsByStylist(String name) {
        return appointmentRepository.findByStylistName(name);
    }
}