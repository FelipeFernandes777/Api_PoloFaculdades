package br.com.api.polofaculdades.models.enterprise.dto;

import br.com.api.polofaculdades.models.enterprise.EnterpriseModel;

import java.time.LocalDateTime;

public record UpdatedEnterpriseDTO(String id, String name, Boolean active, LocalDateTime created_at,LocalDateTime updated_at) {
    public UpdatedEnterpriseDTO(EnterpriseModel model){
        this(model.getId(), model.getName(), model.getActive(), model.getCreatedAt(), model.getUpdatedAt());
    }
}
