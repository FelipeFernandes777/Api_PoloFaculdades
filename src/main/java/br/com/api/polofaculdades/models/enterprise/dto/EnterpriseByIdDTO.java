package br.com.api.polofaculdades.models.enterprise.dto;

import br.com.api.polofaculdades.models.enterprise.EnterpriseModel;
import br.com.api.polofaculdades.models.lead.LeadModel;

import java.time.LocalDateTime;
import java.util.List;

public record EnterpriseByIdDTO(String id, String name, Boolean active, LocalDateTime created_at, List<LeadModel> lead) {
    public EnterpriseByIdDTO (EnterpriseModel model) {
        this(model.getId(), model.getName(), model.getActive(), model.getCreatedAt(), model.getLeads());
    }
}
