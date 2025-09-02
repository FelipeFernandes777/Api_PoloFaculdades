package br.com.api.polofaculdades.services.lead;

import br.com.api.polofaculdades.exception.enterprise.EnterpriseNotFoundException;
import br.com.api.polofaculdades.exception.lead.InvalidLeadDataException;
import br.com.api.polofaculdades.exception.lead.NotFoundLeadException;
import br.com.api.polofaculdades.models.enterprise.EnterpriseModel;
import br.com.api.polofaculdades.models.lead.LeadModel;
import br.com.api.polofaculdades.models.lead.dto.CreateLeadDTO;
import br.com.api.polofaculdades.models.lead.dto.LeadDTO;
import br.com.api.polofaculdades.repositories.EnterpriseRepository;
import br.com.api.polofaculdades.repositories.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeadServices implements ILeadServices {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public Page<LeadDTO> listAllLeads(Pageable pageable) {
        return leadRepository.findAll(pageable).map(LeadDTO::new);
    }

    @Override
    public Optional<LeadDTO> listLeadById(String leadId) {
        return leadRepository.findById(leadId)
                .map(LeadDTO::new)
                .or(() -> { throw new NotFoundLeadException("Lead não encontrado"); });
    }

    @Override
    public LeadDTO createLead(CreateLeadDTO data, String enterpriseId) {
        if (data == null
                || data.name() == null || data.name().isBlank()
                || data.phone() == null || data.phone().isBlank()
                || enterpriseId == null || enterpriseId.isBlank()) {
            throw new InvalidLeadDataException("Dados inválidos para criar o lead");
        }

        EnterpriseModel enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new EnterpriseNotFoundException("Empresa não encontrada"));

        LeadModel newLead = new LeadModel(data, enterprise);
        LeadModel savedLead = leadRepository.save(newLead);

        return new LeadDTO(savedLead);
    }

    @Override
    public Page<LeadDTO> listAllLeadsEnterprise(String enterpriseId, Pageable pageable) {
        if (!enterpriseRepository.existsById(enterpriseId)) {
            throw new EnterpriseNotFoundException("Empresa não encontrada");
        }

        return leadRepository.findByEnterpriseId(enterpriseId, pageable)
                .map(LeadDTO::new);
    }
}
