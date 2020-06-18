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

import java.util.*;

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
        Map<String, String> mapDepartment = separateWhiteSpace(message);

        switch (mapDepartment.get("department")) {
            case "Bolivia":
                Integer totalCases = caseBl.countAllCasesActive();
                Integer deaths = caseBl.countAllByMedCondIdActive(MedicalCondition.Death.getStatus());
                Integer recovered = caseBl.countAllByMedCondIdActive(MedicalCondition.Recovered.getStatus());
                Integer activeCases = caseBl.countAllByMedCondIdActive(MedicalCondition.Confirmed.getStatus());
                Integer todayCases = caseBl.countAllCasesToday();
                Integer todayCasesDeath = caseBl.countAllCasesByMedicalConditionToday(MedicalCondition.Death.getStatus());

                chatResponse.add(concatDataCases("*Bolivia*", totalCases, deaths, recovered, activeCases, todayCases, todayCasesDeath));
                break;

            case Command.startCommand + "Depart":

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

    private Map<String, String> separateWhiteSpace(String temp) {
        Map<String, String> stringMap = new HashMap<>();
        int indexSpace = temp.indexOf(' ');
        if (indexSpace == -1) {
            stringMap.put("starMessage", temp);
            stringMap.put("department", "Bolivia");
        } else {
            String startMessage = temp.substring(0, indexSpace);
            String department = temp.substring(indexSpace + 1);
            stringMap.put("starMessage", startMessage);
            stringMap.put("department", department);
        }

        return stringMap;
    }
}
