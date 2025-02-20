package ucb.edu.bo.sabicovid19.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.bo.sabicovid19.domain.BiUser;

import java.util.List;

public interface BiUserRepository extends JpaRepository<BiUser, Integer> {
    BiUser findByUsernameAndStatus(String username, Integer status);
    BiUser findByUsernameAndPassword(String username, String password);
    List<BiUser> findAllByStatus(Integer status);
}
