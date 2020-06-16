package ucb.edu.bo.sabicovid19.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.bo.sabicovid19.bl.SecurityBl;
import ucb.edu.bo.sabicovid19.model.CredentialModel;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class SecurityController {
    final private SecurityBl securityBl;

    public SecurityController(SecurityBl securityBl) {
        this.securityBl = securityBl;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody CredentialModel credentialsModel) {
        Map<String, String> tokens = securityBl.authenticate(credentialsModel.getUsername(), credentialsModel.getPassword());

        Map <String, Object> response = new HashMap<>();
        if ( tokens != null ) {
            response.put("message", "Authentication OK");
            response.put("authentication", tokens.get("authentication"));
            response.put("refresh", tokens.get("refresh"));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "User or password invalid");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }
}
