package ucb.edu.bo.sabicovid19.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.sabicovid19.domain.BiCase;

import java.util.List;

public interface BiCaseRepository extends JpaRepository<BiCase, Integer> {
    List<BiCase> findAllByStatus(Integer status);
}
