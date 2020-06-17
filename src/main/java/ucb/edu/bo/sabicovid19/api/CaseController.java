package ucb.edu.bo.sabicovid19.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        String tokenJwT = authorization.substring(6);
        log.info("TOKEN JWT: " + tokenJwT);
        DecodedJWT decodedJWT = JWT.decode(tokenJwT);
        String idUser = decodedJWT.getSubject();
        log.info("USER: " + idUser);

        Algorithm algorithm = Algorithm.HMAC256(secretJwt);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Bi-Back-End")
                .build();
        verifier.verify(tokenJwT);
        return ResponseEntity.ok(this.caseBl.findAllCases());
    }

    @PostMapping
    public ResponseEntity<CaseModel> createCustomer(@RequestBody CaseModel caseDto) {
        return new ResponseEntity<>(this.caseBl.createCase(caseDto), HttpStatus.CREATED);
    }

}
