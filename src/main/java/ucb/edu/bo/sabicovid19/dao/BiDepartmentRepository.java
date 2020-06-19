package ucb.edu.bo.sabicovid19.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ucb.edu.bo.sabicovid19.domain.BiDepartment;

import java.util.List;

public interface BiDepartmentRepository extends JpaRepository<BiDepartment, Integer> {
    @Query(value =
            "select * " +
            "from bi_department " +
            "where department like ?1% " +
            "and status = ?2 ", nativeQuery = true)
    List<BiDepartment> findAllDepartmentsBySimilar(String similar, Integer status);
}
