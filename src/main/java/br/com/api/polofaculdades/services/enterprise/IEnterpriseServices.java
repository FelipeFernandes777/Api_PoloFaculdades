package br.com.api.polofaculdades.services.enterprise;

import br.com.api.polofaculdades.models.enterprise.dto.*;
import org.aspectj.apache.bcel.classfile.Unknown;

import java.util.List;
import java.util.Optional;

public interface IEnterpriseServices {
    List<EnterpriseDTO> listAllEnterprisesWhenActiveIsTrue();
    List<EnterpriseDTO> listAllEnterprises();
    Optional<EnterpriseByIdDTO> listEnterpriseById(String id);

    UpdatedEnterpriseDTO updateEnterprise(String id, UpdateEnterpriseDataDTO data);
    EnterpriseDTO createEnterprise(CreateEnterpriseDTO data);

    String enableEnterprise(String id);
    String disableEnterprise(String id);
    void deleteEnterprise(String id);
}
