package ucb.edu.bo.sabicovid19.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.sabicovid19.bl.UserBl;
import ucb.edu.bo.sabicovid19.model.UserModel;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/register-user")
public class UserController {
    final private UserBl userBl;

    public UserController(UserBl userBl) {
        this.userBl = userBl;
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Credentials", "true");
        responseHeaders.set("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
        if (this.userBl.createUser(userModel) == null) {
            return ResponseEntity.status(403).headers(responseHeaders).body(userModel);
            //return new ResponseEntity<>(userModel, HttpStatus.FORBIDDEN);
        }
        return  ResponseEntity.ok().headers(responseHeaders).body(userModel);
        //return new ResponseEntity<>(userModel, HttpStatus.CREATED);
    }
}
