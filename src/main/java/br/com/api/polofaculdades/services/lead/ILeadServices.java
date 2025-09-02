package br.com.api.polofaculdades.services.lead;

import br.com.api.polofaculdades.models.lead.dto.CreateLeadDTO;
import br.com.api.polofaculdades.models.lead.dto.LeadDTO;
import org.aspectj.apache.bcel.classfile.Unknown;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ILeadServices {
    Page<LeadDTO> listAllLeads(Pageable pageable);
    Optional<LeadDTO> listLeadById(String leadId);
    LeadDTO createLead(CreateLeadDTO data, String enterpriseId);
    Page<LeadDTO> listAllLeadsEnterprise(String enterpriseId, Pageable pageable);
}
