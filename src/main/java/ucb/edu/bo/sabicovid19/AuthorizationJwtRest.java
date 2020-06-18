package ucb.edu.bo.sabicovid19;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class AuthorizationJwtRest {
    private final String authorization;
    private final String secretJwt;

    public boolean validateToken() {
        try {
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
            return true;
        } catch (SignatureVerificationException e) {
            log.error(e.getMessage());
            return false;
        }

    }
}
