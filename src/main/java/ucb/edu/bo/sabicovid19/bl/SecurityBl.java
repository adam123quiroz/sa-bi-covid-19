package ucb.edu.bo.sabicovid19.bl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ucb.edu.bo.sabicovid19.dao.BiUserRepository;
import ucb.edu.bo.sabicovid19.domain.BiUser;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SecurityBl {
    final BiUserRepository biUserRepository;

    @Value("${sa-bi-covid-19.security.salt}")
    private String salt;

    @Value("${sa-bi-covid-19.security.secretJwt}")
    private String secretJwt;

    public SecurityBl(BiUserRepository biUserRepository) {
        this.biUserRepository = biUserRepository;
    }

    public Map<String, String> authenticate(String username, String password) {
        Map<String, String> result = new HashMap<>();
        String sha256hex = Hashing.sha256()
                .hashString(password + salt, StandardCharsets.UTF_8)
                .toString();
        BiUser user = biUserRepository.findByUsernameAndPassword(username, password);

        if (user == null) {
            return null;
        } else {
            Integer userId = user.getUserId();
            result.put("authentication", generateJWT(userId, 30, "AUTHN"));
            result.put("refresh", generateJWT(userId, 6, "REFRESH"));
            return result;
        }
    }

    private String generateJWT(Integer userId, int minutes, String type) {
        LocalDateTime expiresAt = LocalDateTime.now(ZoneId.systemDefault()).plusMinutes(minutes);
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretJwt);
            token = JWT.create()
                    .withIssuer("Bi-Back-End")
                    .withClaim("type", type)
                    .withSubject(userId.toString())
                    .withExpiresAt(Date.from(expiresAt.atZone(ZoneId.systemDefault()).toInstant()))
                    .sign(algorithm);

        } catch (JWTCreationException ex){
            throw new RuntimeException(ex);
        }
        return token;
    }

}
