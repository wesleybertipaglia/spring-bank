package com.wesleybertipaglia.bank.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.bank.dtos.AgencyDTO;
import com.wesleybertipaglia.bank.models.Agency;
import com.wesleybertipaglia.bank.services.AgencyService;

@RestController
@RequestMapping("/agencies")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @PostMapping("/")
    public AgencyDTO createAgency(@RequestParam UUID bankId, @RequestBody Agency agency) {
        return agencyService.createAgency(bankId, agency);
    }

    @GetMapping("/")
    public List<AgencyDTO> listAgencies() {
        return agencyService.listAgencies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgencyDTO> getAgencyById(@PathVariable UUID id) {
        Optional<AgencyDTO> agency = agencyService.getAgencyById(id);
        return agency.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgencyDTO> updateAgency(@PathVariable UUID id, @RequestBody Agency agency) {
        Optional<AgencyDTO> updatedAgency = agencyService.updateAgency(id, agency);
        return updatedAgency.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable UUID id) {
        try {
            agencyService.deleteAgency(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
