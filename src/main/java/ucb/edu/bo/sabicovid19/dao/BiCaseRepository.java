package ucb.edu.bo.sabicovid19.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.sabicovid19.domain.BiCase;
import ucb.edu.bo.sabicovid19.domain.BiMedicalCondition;

import java.util.Date;
import java.util.List;

public interface BiCaseRepository extends JpaRepository<BiCase, Integer> {
    List<BiCase> findAllByStatus(Integer status);

    Integer countAllByMedCondIdAndStatus(BiMedicalCondition medicalCondition, Integer status);
    Integer countAllByMedCondIdNot(BiMedicalCondition medicalCondition);
    Integer countAllByUpdateDate(Date date);
    Integer countAllByMedCondIdAndUpdateDateAndStatus(BiMedicalCondition medicalCondition, Date dateNow, Integer status);

    Integer countByMunicipallyId_ProvinceId_DepartmentId_DepartmentAndMedCondIdAndStatus(String department, BiMedicalCondition medicalCondition, Integer status);
    Integer countByMunicipallyId_ProvinceId_DepartmentId_DepartmentAndMedCondIdNot(String department, BiMedicalCondition medicalCondition);
    Integer countByMunicipallyId_ProvinceId_DepartmentId_DepartmentAndUpdateDate(String department, Date date);
    Integer countByMunicipallyId_ProvinceId_DepartmentId_DepartmentAndMedCondIdAndUpdateDateAndStatus(String department, BiMedicalCondition medicalCondition, Date date, Integer status);
}
