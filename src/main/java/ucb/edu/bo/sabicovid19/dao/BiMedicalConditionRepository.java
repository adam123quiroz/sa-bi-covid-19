package ucb.edu.bo.sabicovid19.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.sabicovid19.domain.BiMedicalCondition;

public interface BiMedicalConditionRepository extends JpaRepository<BiMedicalCondition, Integer> {
    BiMedicalCondition findByMedCondIdAndStatus(Integer id, Integer status);
}
