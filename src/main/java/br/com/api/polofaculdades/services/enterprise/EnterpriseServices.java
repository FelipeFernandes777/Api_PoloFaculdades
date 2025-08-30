package br.com.api.polofaculdades.services.enterprise;

import br.com.api.polofaculdades.exception.enterprise.EnterpriseNotFoundException;
import br.com.api.polofaculdades.exception.enterprise.InvalidEnterpriseDataException;
import br.com.api.polofaculdades.models.enterprise.EnterpriseModel;
import br.com.api.polofaculdades.models.enterprise.dto.*;
import br.com.api.polofaculdades.repositories.EnterpriseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseServices implements IEnterpriseServices {
    @Autowired
    private EnterpriseRepository repository;

    @Override
    public List<EnterpriseDTO> listAllEnterprisesWhenActiveIsTrue() {
        var enterprises = this.repository.findAllByActiveIsTrue();

        if (enterprises.isEmpty()) {
            throw new EnterpriseNotFoundException("Nenhuma empresa ativa encontrada");
        }

        return enterprises.stream()
                .map(EnterpriseDTO::new)
                .toList();
    }

    @Override
    public List<EnterpriseDTO> listAllEnterprises() {
        var enterprises = this.repository.findAll();

        if (enterprises.isEmpty()) {
            throw new EnterpriseNotFoundException("Nenhuma empresa encontrada");
        }

        return enterprises.stream()
                .map(EnterpriseDTO::new)
                .toList();
    }

    @Override
    public Optional<EnterpriseByIdDTO> listEnterpriseById(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidEnterpriseDataException("Favor informar o ID");
        }

        var enterprise = this.repository.findById(id)
                .orElseThrow(() -> new EnterpriseNotFoundException("Nenhuma empresa encontrada com esse ID"));

        return Optional.of(new EnterpriseByIdDTO(enterprise));
    }

    @Transactional
    @Override
    public UpdatedEnterpriseDTO updateEnterprise(String id, UpdateEnterpriseDataDTO data) {
        if (id == null || id.isEmpty()) {
            throw new InvalidEnterpriseDataException("Favor informar o ID");
        }

        var enterprise = this.repository.findById(id)
                .orElseThrow(() -> new EnterpriseNotFoundException("Empresa não encontrada para atualização"));

        return enterprise.update(data);
    }

    @Transactional
    @Override
    public EnterpriseDTO createEnterprise(CreateEnterpriseDTO data) {
        if (data.name() == null || data.name().isEmpty()) {
            throw new InvalidEnterpriseDataException("É necessário informar o nome da empresa");
        }

        var newEnterprise = new EnterpriseModel(data);
        this.repository.save(newEnterprise);

        return new EnterpriseDTO(newEnterprise);
    }

    @Transactional
    @Override
    public String enableEnterprise(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidEnterpriseDataException("É necessário informar o Id da empresa");
        }

        var enterprise = this.repository.findById(id)
                .orElseThrow(() -> new EnterpriseNotFoundException("Empresa não encontrada para ativação"));

        enterprise.enable();
        this.repository.save(enterprise);

        return enterprise.getId();
    }

    @Transactional
    @Override
    public String disableEnterprise(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidEnterpriseDataException("É necessário informar o Id da empresa");
        }

        var enterprise = this.repository.findById(id)
                .orElseThrow(() -> new EnterpriseNotFoundException("Empresa não encontrada para desativação"));

        enterprise.disable();
        this.repository.save(enterprise);

        return enterprise.getId();
    }

    @Transactional
    @Override
    public void deleteEnterprise(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidEnterpriseDataException("É necessário informar o Id da empresa");
        }

        if (!this.repository.existsById(id)) {
            throw new EnterpriseNotFoundException("Empresa não encontrada para exclusão");
        }

        this.repository.deleteById(id);
    }
}
