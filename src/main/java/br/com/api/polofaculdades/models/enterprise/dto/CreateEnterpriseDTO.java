package br.com.api.polofaculdades.models.enterprise.dto;

public record CreateEnterpriseDTO(String name, Boolean active) {
    public CreateEnterpriseDTO {
        if(active == null) {
            active = true;
        }
    }
}
