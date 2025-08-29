package br.com.api.polofaculdades.repositories;

import br.com.api.polofaculdades.models.lead.LeadModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<LeadModel, String> {
}
