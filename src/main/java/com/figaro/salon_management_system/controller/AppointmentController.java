package com.figaro.salon_management_system.controller;

import com.figaro.salon_management_system.model.Appointment;
import com.figaro.salon_management_system.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @GetMapping("/customer/{email}")
    public List<Appointment> getByCustomer(@PathVariable String email) {
        return appointmentService.getAppointmentsByCustomer(email);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PutMapping("/{id}/status")
    public Appointment updateStatus(@PathVariable Long id, @RequestParam String status) {
        return appointmentService.updateAppointmentStatus(id, status);
    }

    @PutMapping("/{id}/assign")
    public Appointment assignStylist(@PathVariable Long id, @RequestParam Long stylistId) {
        return appointmentService.assignStylist(id, stylistId);
    }

    @GetMapping("/stylist/{name}")
    public List<Appointment> getByStylist(@PathVariable String name) {
        return appointmentService.getAppointmentsByStylist(name);
    }
}
