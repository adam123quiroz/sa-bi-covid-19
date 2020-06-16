package ucb.edu.bo.sabicovid19.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.sabicovid19.domain.BiGender;

public interface BiGenderRepository extends JpaRepository<BiGender, Integer> {
    BiGender findByGenderIdAndStatus(Integer id, Integer status);
}
