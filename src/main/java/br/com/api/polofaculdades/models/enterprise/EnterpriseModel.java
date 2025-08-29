package br.com.api.polofaculdades.models.enterprise;

import br.com.api.polofaculdades.models.enterprise.dto.CreateEnterpriseDTO;
import br.com.api.polofaculdades.models.enterprise.dto.UpdateEnterpriseDataDTO;
import br.com.api.polofaculdades.models.enterprise.dto.UpdatedEnterpriseDTO;
import br.com.api.polofaculdades.models.lead.LeadModel;
import br.com.api.polofaculdades.models.lead.dto.LeadDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "empresas")
@Entity(name = "empresa")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class EnterpriseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nome_empresa", nullable = false, length = 50)
    private String name;

    @Column(name = "ativa", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "enterprise")
    private List<LeadModel> leads;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public EnterpriseModel(CreateEnterpriseDTO data) {
        this.name = data.name();
        this.active = data.active();
    }

    public UpdatedEnterpriseDTO update(UpdateEnterpriseDataDTO data) {
        this.name = data.name();
        return new UpdatedEnterpriseDTO(this);
    }

    public void disable() {
        this.active = false;
        this.deletedAt = LocalDateTime.now();
    }

    public void enable() {
        this.active = true;
        this.deletedAt = null;
    }
}
