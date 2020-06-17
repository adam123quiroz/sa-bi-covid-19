package ucb.edu.bo.sabicovid19.bl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ucb.edu.bo.sabicovid19.Status;
import ucb.edu.bo.sabicovid19.dao.BiUserRepository;
import ucb.edu.bo.sabicovid19.domain.BiUser;
import ucb.edu.bo.sabicovid19.dto.UserDto;

import java.util.*;

@Service
public class UserBl  {
    final
    BiUserRepository userRepository;

    public UserBl(BiUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<BiUser>findRecentUsers(PageRequest page) {
        return this.userRepository.findAll(page).getContent();
    }

    public Optional<BiUser> findById(Integer id) {
        return this.userRepository.findById(id);
    }

    public BiUser updateUser(BiUser user) {
        return this.userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteById(id);
    }


    /**
     * this method converts the original BiUser to UserDto for read it easily
     * @return A list UserDto
     */
    public List<UserDto> findAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        this.userRepository.findAllByStatus(Status.ACTIVE.getStatus()).forEach(user -> {
            UserDto userDto = new UserDto(user);
            userDtoList.add(userDto);
        });
        return userDtoList;
    }
}
