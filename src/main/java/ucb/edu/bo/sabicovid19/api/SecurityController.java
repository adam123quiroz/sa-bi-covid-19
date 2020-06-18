package ucb.edu.bo.sabicovid19.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.sabicovid19.bl.SecurityBl;
import ucb.edu.bo.sabicovid19.model.CredentialModel;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
public class SecurityController {
    final private SecurityBl securityBl;

    public SecurityController(SecurityBl securityBl) {
        this.securityBl = securityBl;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody CredentialModel credentialsModel) {
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.set("Access-Control-Allow-Origin", "http://localhost:4200");
        responseHeaders.set("Access-Control-Allow-Credentials", "true");
        responseHeaders.set("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
        Map<String, String> tokens = securityBl.authenticate(credentialsModel.getUsername(), credentialsModel.getPassword());
        Map <String, Object> response = new HashMap<>();
        if ( tokens != null ) {
            response.put("message", "Authentication OK");
            response.put("authentication", tokens.get("authentication"));
            response.put("refresh", tokens.get("refresh"));
            return ResponseEntity.ok().headers(responseHeaders).body(response);
            //return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "User or password invalid");
            response.put("authentication", "0");
            response.put("refresh", "0");
            return ResponseEntity.ok().headers(responseHeaders).body(response);
            //return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }
}
