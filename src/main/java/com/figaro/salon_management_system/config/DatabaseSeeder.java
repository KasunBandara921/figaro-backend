package com.figaro.salon_management_system.config;

import com.figaro.salon_management_system.model.Role;
import com.figaro.salon_management_system.model.ServiceItem;
import com.figaro.salon_management_system.model.Stylist;
import com.figaro.salon_management_system.model.User;
import com.figaro.salon_management_system.repository.AppointmentRepository;
import com.figaro.salon_management_system.repository.ServiceRepository;
import com.figaro.salon_management_system.repository.StylistRepository;
import com.figaro.salon_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private StylistRepository stylistRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing database with seeding checks...");
        seedUsers();
        seedServices();
        seedStylists();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            // Seed Admin
            User admin = new User();
            admin.setFullName("Salon Admin");
            admin.setEmail("admin@figarosalon.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);

            // Seed Stylist User
            User stylistUser = new User();
            stylistUser.setFullName("Sarah Connor");
            stylistUser.setEmail("stylist@figarosalon.com");
            stylistUser.setPassword(passwordEncoder.encode("stylist123"));
            stylistUser.setRole(Role.STYLIST);
            userRepository.save(stylistUser);

            // Seed Customer User
            User customer = new User();
            customer.setFullName("John Doe");
            customer.setEmail("customer@figarosalon.com");
            customer.setPassword(passwordEncoder.encode("customer123"));
            customer.setRole(Role.CUSTOMER);
            userRepository.save(customer);

            System.out.println(
                    "✅ Seeded default users (Admin: admin@figarosalon.com/admin123, Stylist: stylist@figarosalon.com/stylist123, Customer: customer@figarosalon.com/customer123)");
        }
    }

    private void seedServices() {
        if (serviceRepository.count() == 0) {
            ServiceItem haircut = new ServiceItem();
            haircut.setName("Haircut & Trim");
            haircut.setDescription("Standard cut, wash, and style tailored to your face shape and hair type.");
            haircut.setPrice(35.00);
            haircut.setDurationMinutes(30);

            ServiceItem color = new ServiceItem();
            color.setName("Hair Coloring");
            color.setDescription("Full cover coloring, roots, highlights or custom balayage.");
            color.setPrice(90.00);
            color.setDurationMinutes(120);

            ServiceItem beard = new ServiceItem();
            beard.setName("Beard Styling & Shave");
            beard.setDescription("Precise trimming, line up, hot towel shave, and beard conditioning treatment.");
            beard.setPrice(25.00);
            beard.setDurationMinutes(30);

            ServiceItem blowout = new ServiceItem();
            blowout.setName("Blow Wash & Style");
            blowout.setDescription("Relaxing hair wash followed by a bouncy professional blowout.");
            blowout.setPrice(30.00);
            blowout.setDurationMinutes(40);

            ServiceItem facial = new ServiceItem();
            facial.setName("Facial Treatment");
            facial.setDescription("Deep pore cleansing, skin exfoliation, and soothing face massage.");
            facial.setPrice(50.00);
            facial.setDurationMinutes(45);

            serviceRepository.saveAll(Arrays.asList(haircut, color, beard, blowout, facial));
            System.out.println("✅ Seeded default services");
        }
    }

    private void seedStylists() {
        if (stylistRepository.count() == 0) {
            Stylist stylist1 = new Stylist();
            stylist1.setName("Sarah Connor");
            stylist1.setRating(4.9);
            stylist1.setSpecialties(Arrays.asList("Haircut & Trim", "Blow Wash & Style"));

            Stylist stylist2 = new Stylist();
            stylist2.setName("Marcus Aurelius");
            stylist2.setRating(4.8);
            stylist2.setSpecialties(Arrays.asList("Beard Styling & Shave", "Haircut & Trim"));

            Stylist stylist3 = new Stylist();
            stylist3.setName("Jane Doe");
            stylist3.setRating(4.7);
            stylist3.setSpecialties(Arrays.asList("Hair Coloring", "Blow Wash & Style", "Facial Treatment"));

            stylistRepository.saveAll(Arrays.asList(stylist1, stylist2, stylist3));
            System.out.println("✅ Seeded default stylists");
        }
    }
}
