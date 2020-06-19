package ucb.edu.bo.sabicovid19.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.sabicovid19.AuthorizationJwtRest;
import ucb.edu.bo.sabicovid19.bl.BotTelegramBl;
import ucb.edu.bo.sabicovid19.model.MessageReportModel;
import ucb.edu.bo.sabicovid19.model.MessageUser;

@Slf4j
@RestController
@RequestMapping("/bot-telegram")
public class BotTelegramController {
    final BotTelegramBl botTelegramBl;

    @Value("${sa-bi-covid-19.security.secretJwt}")
    private String secretJwt;

    public BotTelegramController(BotTelegramBl botTelegramBl) {
        this.botTelegramBl = botTelegramBl;
    }

    @PostMapping
    public ResponseEntity<MessageReportModel> findAllInformation(@RequestHeader("Authorization") String authorization,
                                                                 @RequestBody MessageUser messageUser) {
        AuthorizationJwtRest authorizationJwtRest = new AuthorizationJwtRest(authorization, secretJwt);
        if (authorizationJwtRest.validateToken()) {
            if (messageUser.getIsDepartment().equalsIgnoreCase("NO")) {
                return ResponseEntity.ok(this.botTelegramBl.findAllCasesBolivia());
            }
            if (messageUser.getIsDepartment().equalsIgnoreCase("SI")){
                return ResponseEntity.ok(this.botTelegramBl.findAllCasesByDepartment(
                        messageUser.getPlace()
                ));
            }
        } else {
            return new ResponseEntity<>(new MessageReportModel(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(new MessageReportModel(), HttpStatus.BAD_REQUEST);
    }
}
