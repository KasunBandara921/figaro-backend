package com.figaro.salon_management_system.service;

import com.figaro.salon_management_system.model.Appointment;
import com.figaro.salon_management_system.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

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
}