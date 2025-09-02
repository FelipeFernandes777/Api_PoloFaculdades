package br.com.api.polofaculdades.models.lead.dto;

import br.com.api.polofaculdades.models.lead.LeadModel;

import java.time.LocalDateTime;

public record LeadDTO(String id, String name, String phone, LocalDateTime createdAt) {
    public LeadDTO(LeadModel model){
        this(model.getId(),model.getName(), model.getPhone(), model.getCreatedAt());
    }
}
