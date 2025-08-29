package br.com.api.polofaculdades.repositories;

import br.com.api.polofaculdades.models.enterprise.EnterpriseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnterpriseRepository extends JpaRepository<EnterpriseModel, String> {
    List<EnterpriseModel> findAllByActiveIsTrue();
}
