package ucb.edu.bo.sabicovid19.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.sabicovid19.bl.BotTelegramBl;
import ucb.edu.bo.sabicovid19.model.MessageReportModel;
import ucb.edu.bo.sabicovid19.model.MessageUser;

@Slf4j
@RestController
@RequestMapping("/bot-telegram")
public class BotTelegramController {
    final BotTelegramBl botTelegramBl;

    public BotTelegramController(BotTelegramBl botTelegramBl) {
        this.botTelegramBl = botTelegramBl;
    }

    @PostMapping
    public ResponseEntity<MessageReportModel> findAllInformation(@RequestBody MessageUser messageUser) {
        if (messageUser.getIsDepartment().equalsIgnoreCase("NO")) {
            return ResponseEntity.ok(this.botTelegramBl.findAllCasesBolivia());
        }
        if (messageUser.getIsDepartment().equalsIgnoreCase("SI")){
            return ResponseEntity.ok(this.botTelegramBl.findAllCasesByDepartment(
                    messageUser.getPlace()
            ));
        }
        return new ResponseEntity<>(new MessageReportModel(), HttpStatus.BAD_REQUEST);
    }
}
