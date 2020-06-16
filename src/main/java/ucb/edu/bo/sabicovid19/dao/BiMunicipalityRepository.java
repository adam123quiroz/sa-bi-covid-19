package ucb.edu.bo.sabicovid19.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.sabicovid19.domain.BiMunicipality;

public interface BiMunicipalityRepository extends JpaRepository<BiMunicipality, Integer> {
    BiMunicipality findByMunicipallyIdAndStatus(Integer id, Integer status);
}
