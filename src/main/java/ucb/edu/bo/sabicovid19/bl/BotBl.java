package ucb.edu.bo.sabicovid19.bl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ucb.edu.bo.sabicovid19.MedicalCondition;
import ucb.edu.bo.sabicovid19.Status;
import ucb.edu.bo.sabicovid19.bot.commands.Command;
import ucb.edu.bo.sabicovid19.dao.BiCaseRepository;
import ucb.edu.bo.sabicovid19.dao.BiMedicalConditionRepository;
import ucb.edu.bo.sabicovid19.domain.BiMedicalCondition;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BotBl {
    final
    CaseBl caseBl;
    final
    MedicalConditionBl medicalConditionBl;

    public BotBl(CaseBl caseBl, MedicalConditionBl medicalConditionBl) {
        this.caseBl = caseBl;
        this.medicalConditionBl = medicalConditionBl;
    }

    public List<String> processUpdate(Update update) {
        List<String> chatResponse = new ArrayList<>();
        continueChat(update, chatResponse);
        return chatResponse;
    }

    private void continueChat(Update update, List<String> chatResponse) {
        String message = update.getMessage().getText();

        switch (message) {
            case Command.startCommand:
                Integer totalCases = caseBl.countAllCasesActive();
                Integer deaths = caseBl.countAllByMedCondIdActive(MedicalCondition.Death.getStatus());
                Integer recovered = caseBl.countAllByMedCondIdActive(MedicalCondition.Recovered.getStatus());
                Integer activeCases = caseBl.countAllByMedCondIdActive(MedicalCondition.Confirmed.getStatus());
                Integer todayCases = caseBl.countAllCasesToday();
                Integer todayCasesDeath = caseBl.countAllCasesByMedicalConditionToday(MedicalCondition.Death.getStatus());

                chatResponse.add(concatDataCases("Bolivia", totalCases, deaths, recovered, activeCases, todayCases, todayCasesDeath));
                break;
            default:
                chatResponse.add("Intenta de nuevo");
        }
    }

    private String concatDataCases(String location, Integer totalCases, Integer deaths, Integer recovered, Integer activeCase, Integer todayCases, Integer todayDeaths) {
        return location +
                String.format("\n*Casos:* %d\n*Decesos:* %d\n*Recuperados:* %d\n*Casos Activos:* %d\n\n*Casos de Hoy:* %d\n*Muertes de Hoy:* %d",
                        totalCases, deaths, recovered, activeCase, todayCases, todayDeaths);
    }
}
