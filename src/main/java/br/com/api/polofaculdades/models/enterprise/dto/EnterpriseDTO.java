package br.com.api.polofaculdades.models.enterprise.dto;

import br.com.api.polofaculdades.models.enterprise.EnterpriseModel;

import java.time.LocalDateTime;

public record EnterpriseDTO(String id, String name, Boolean active, LocalDateTime created_at) {
    public EnterpriseDTO(EnterpriseModel model){
        this(model.getId(), model.getName(), model.getActive(), model.getCreatedAt());
    }
}
