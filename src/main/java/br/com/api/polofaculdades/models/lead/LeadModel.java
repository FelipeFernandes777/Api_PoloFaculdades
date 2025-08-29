package br.com.api.polofaculdades.models.lead;

import br.com.api.polofaculdades.models.enterprise.EnterpriseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "leads")
@Entity(name = "lead")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class LeadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "telefone", nullable = false, length = 11)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private EnterpriseModel enterprise;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
