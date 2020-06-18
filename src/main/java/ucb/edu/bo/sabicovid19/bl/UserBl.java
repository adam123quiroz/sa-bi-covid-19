package ucb.edu.bo.sabicovid19.bl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ucb.edu.bo.sabicovid19.Status;
import ucb.edu.bo.sabicovid19.dao.BiUserRepository;
import ucb.edu.bo.sabicovid19.domain.BiUser;
import ucb.edu.bo.sabicovid19.dto.UserDto;
import ucb.edu.bo.sabicovid19.model.UserModel;

import java.util.*;

@Slf4j
@Service
public class UserBl {
    final
    BiUserRepository userRepository;

    public UserBl(BiUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * this method, create a new user, like a bl
     *
     * @param userModel the model like the json comes in
     * @return return a model like a confirmation
     */
    public UserModel createUser(UserModel userModel) {
        BiUser usernameIfExists = this.userRepository.findByUsernameAndStatus(userModel.getUsername(), Status.ACTIVE.getStatus());
        log.info("Username " + userModel.getUsername());

        if (usernameIfExists == null) {
            BiUser user = new BiUser();
            user.setUsername(userModel.getUsername());
            user.setPassword(userModel.getPassword());

            user.setStatus(Status.ACTIVE.getStatus());
            user.setTextDate(new Date());
            user.setTextHost("localhost");
            user.setTextUser("Admin");

            log.info(user.toString());

            this.userRepository.save(user);
            return userModel;
        } else {
            return null;
        }
    }

    public List<BiUser> findRecentUsers(PageRequest page) {
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
     *
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
