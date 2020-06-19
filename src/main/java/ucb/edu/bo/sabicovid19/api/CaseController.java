package ucb.edu.bo.sabicovid19.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.sabicovid19.AuthorizationJwtRest;
import ucb.edu.bo.sabicovid19.bl.CaseBl;
import ucb.edu.bo.sabicovid19.dto.CaseDto;
import ucb.edu.bo.sabicovid19.model.CaseModel;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/register-case")
public class CaseController {
    final
    private CaseBl caseBl;

    @Value("${sa-bi-covid-19.security.secretJwt}")
    private String secretJwt;

    public CaseController(CaseBl caseBl) {
        this.caseBl = caseBl;
    }

    @GetMapping
    public ResponseEntity<List<CaseDto>> findAllCases(@RequestHeader("Authorization") String authorization) {
        AuthorizationJwtRest authorizationJwtRest = new AuthorizationJwtRest(authorization, secretJwt);
        if (authorizationJwtRest.validateToken()) {
            return ResponseEntity.ok(this.caseBl.findAllCases());
        } else {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

    }

    @PostMapping
    public ResponseEntity<CaseModel> createCustomer(@RequestBody CaseModel caseDto,
                                                    @RequestHeader("Authorization") String authorization) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Credentials", "true");
        responseHeaders.set("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
        AuthorizationJwtRest authorizationJwtRest = new AuthorizationJwtRest(authorization, secretJwt);
        if (authorizationJwtRest.validateToken()) {
            return ResponseEntity.ok().headers(responseHeaders).body(this.caseBl.createCase((caseDto)));
            //return new ResponseEntity<>(this.caseBl.createCase(caseDto), HttpStatus.CREATED);
        }
        return ResponseEntity.status(403).headers(responseHeaders).body(null);
        //return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

}
