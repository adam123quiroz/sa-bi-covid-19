package ucb.edu.bo.sabicovid19.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ucb.edu.bo.sabicovid19.domain.BiCase;
import ucb.edu.bo.sabicovid19.domain.BiMedicalCondition;
import ucb.edu.bo.sabicovid19.domain.BiMedicalCondition_;

import java.util.Date;
import java.util.List;

public interface BiCaseRepository extends JpaRepository<BiCase, Integer> {
    List<BiCase> findAllByStatus(Integer status);

    Integer countAllByMedCondIdAndStatus(BiMedicalCondition medicalCondition, Integer status);

    Integer countAllByMedCondIdNot(BiMedicalCondition medicalCondition);

    Integer countAllByUpdateDate(Date date);

    Integer countAllByMedCondIdAndUpdateDateAndStatus(BiMedicalCondition medicalCondition, Date dateNow, Integer status);
}
