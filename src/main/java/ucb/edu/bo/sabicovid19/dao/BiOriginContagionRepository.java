package ucb.edu.bo.sabicovid19.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.sabicovid19.domain.BiOriginContagion;

public interface BiOriginContagionRepository extends JpaRepository<BiOriginContagion, Integer> {
    BiOriginContagion findByOriContgIdAndStatus(Integer id, Integer status);
}
