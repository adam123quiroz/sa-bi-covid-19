package ucb.edu.bo.sabicovid19.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.sabicovid19.AuthorizationJwtRest;
import ucb.edu.bo.sabicovid19.bl.CaseBl;
import ucb.edu.bo.sabicovid19.dto.CaseDto;
import ucb.edu.bo.sabicovid19.model.CaseModel;

import java.util.List;

@Slf4j
@RestController
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
        AuthorizationJwtRest authorizationJwtRest = new AuthorizationJwtRest(authorization, secretJwt);
        if (authorizationJwtRest.validateToken()) {
            return new ResponseEntity<>(this.caseBl.createCase(caseDto), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

}
