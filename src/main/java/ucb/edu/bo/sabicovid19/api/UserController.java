package ucb.edu.bo.sabicovid19.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.sabicovid19.bl.UserBl;
import ucb.edu.bo.sabicovid19.model.UserModel;

@Slf4j
@RestController
@RequestMapping("/register-user")
public class UserController {
    final private UserBl userBl;

    public UserController(UserBl userBl) {
        this.userBl = userBl;
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        if (this.userBl.createUser(userModel) == null) {
            return new ResponseEntity<>(userModel, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(userModel, HttpStatus.CREATED);
    }
}
