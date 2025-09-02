package br.com.api.polofaculdades.controllers;

import br.com.api.polofaculdades.exception.lead.NotFoundLeadException;
import br.com.api.polofaculdades.models.lead.dto.CreateLeadDTO;
import br.com.api.polofaculdades.models.lead.dto.LeadDTO;
import br.com.api.polofaculdades.services.lead.LeadServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lead")
public class LeadController {

    private final LeadServices services;

    public LeadController(LeadServices leadServices) {
        this.services = leadServices;
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<LeadDTO>> listAllLeads(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(services.listAllLeads(pageable));
    }

    @GetMapping("/empresa/{enterpriseId}")
    public ResponseEntity<Page<LeadDTO>> listLeadsByEnterprise(
            @PathVariable String enterpriseId,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(services.listAllLeadsEnterprise(enterpriseId, pageable));
    }

    @PostMapping("/criar/{enterpriseId}")
    public ResponseEntity<LeadDTO> createLead(
            @PathVariable String enterpriseId,
            @RequestBody CreateLeadDTO data) {
        LeadDTO createdLead = services.createLead(data, enterpriseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLead);
    }

    @GetMapping("/{leadId}")
    public ResponseEntity<LeadDTO> getLeadById(@PathVariable String leadId) {
        LeadDTO lead = services.listLeadById(leadId)
                .orElseThrow(() -> new NotFoundLeadException("Lead não encontrado"));
        return ResponseEntity.ok(lead);
    }
}
