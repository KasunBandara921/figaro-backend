package com.figaro.salon_management_system.controller;

import com.figaro.salon_management_system.model.Stylist;
import com.figaro.salon_management_system.service.StylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stylists")
public class StylistController {

    @Autowired
    private StylistService stylistService;

    @GetMapping
    public List<Stylist> getAllStylists() {
        return stylistService.getAllStylists();
    }

    @PostMapping
    public Stylist createStylist(@RequestBody Stylist stylist) {
        return stylistService.createStylist(stylist);
    }

    @PutMapping("/{id}")
    public Stylist updateStylist(@PathVariable Long id, @RequestBody Stylist stylist) {
        return stylistService.updateStylist(id, stylist);
    }
}