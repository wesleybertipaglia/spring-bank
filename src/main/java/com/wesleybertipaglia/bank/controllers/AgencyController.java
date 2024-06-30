package com.wesleybertipaglia.bank.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.bank.dtos.AgencyDTO;
import com.wesleybertipaglia.bank.services.AgencyService;

@RestController
@RequestMapping("/api/v1/agencies")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @PostMapping("/")
    public ResponseEntity<AgencyDTO> createAgency(@RequestBody AgencyDTO agencyDTO) {
        return ResponseEntity.of(agencyService.createAgency(agencyDTO));
    }

    @GetMapping("/")
    public ResponseEntity<Page<AgencyDTO>> listAgencies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(agencyService.listAgencies(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgencyDTO> getAgencyById(@PathVariable UUID id) {
        return ResponseEntity.of(agencyService.getAgencyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgencyDTO> updateAgency(@PathVariable UUID id, @RequestBody AgencyDTO agencyDTO) {
        return ResponseEntity.of(agencyService.updateAgency(id, agencyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgency(@PathVariable UUID id) {
        return ResponseEntity.of(agencyService.deleteAgency(id));
    }

}
