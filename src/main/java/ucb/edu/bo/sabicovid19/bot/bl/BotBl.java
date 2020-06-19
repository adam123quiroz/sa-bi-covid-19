package ucb.edu.bo.sabicovid19.bot.bl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ucb.edu.bo.sabicovid19.bl.DepartmentBl;
import ucb.edu.bo.sabicovid19.dto.DepartmentDto;
import ucb.edu.bo.sabicovid19.enums.MedicalCondition;
import ucb.edu.bo.sabicovid19.bl.CaseBl;
import ucb.edu.bo.sabicovid19.bl.MedicalConditionBl;

import java.util.*;

@Slf4j
@Service
public class BotBl {
    final
    CaseBl caseBl;
    final
    MedicalConditionBl medicalConditionBl;
    final
    DepartmentBl departmentBl;

    public BotBl(CaseBl caseBl, MedicalConditionBl medicalConditionBl, DepartmentBl departmentBl) {
        this.caseBl = caseBl;
        this.medicalConditionBl = medicalConditionBl;
        this.departmentBl = departmentBl;
    }

    public List<String> processUpdate(Update update) {
        List<String> chatResponse = new ArrayList<>();
        continueChat(update, chatResponse);
        return chatResponse;
    }

    private void continueChat(Update update, List<String> chatResponse) {
        String message = update.getMessage().getText();
        Map<String, String> mapDepartment = separateWhiteSpace(message);

        switch (mapDepartment.get("isDepartment")) {
            case "NO":
                Integer totalCases = caseBl.countAllCasesActive();
                Integer deaths = caseBl.countAllByMedCondIdActive(MedicalCondition.Death.getStatus());
                Integer recovered = caseBl.countAllByMedCondIdActive(MedicalCondition.Recovered.getStatus());
                Integer activeCases = caseBl.countAllByMedCondIdActive(MedicalCondition.Confirmed.getStatus());

                Integer todayCases = caseBl.countAllCasesToday();
                Integer todayCasesDeath = caseBl.countAllCasesByMedicalConditionToday(MedicalCondition.Death.getStatus());

                chatResponse.add(concatDataCases("*Bolivia*", totalCases, deaths, recovered, activeCases, todayCases, todayCasesDeath));
                break;

            case "SI":
                String department = mapDepartment.get("department");
                Integer totalCasesByDepartment = caseBl.countAllCasesActiveByDepartment(department);
                Integer deathsByDepartment = caseBl.countAllByDepartmentAndMedicalConditionActive(
                        department,
                        MedicalCondition.Death.getStatus()
                );
                Integer recoveredByDepartment = caseBl.countAllByDepartmentAndMedicalConditionActive(
                        department,
                        MedicalCondition.Recovered.getStatus()
                );
                Integer activeCasesByDepartment = caseBl.countAllByDepartmentAndMedicalConditionActive(
                        department,
                        MedicalCondition.Confirmed.getStatus()
                );

                Integer todayCasesByDepartment = caseBl.countAllCasesTodayByDepartment(department);
                Integer todayDeathCasesByDepartment = caseBl.countAllCasesByMedicalConditionToday(department);
                chatResponse.add(
                        concatDataCases(department, totalCasesByDepartment, deathsByDepartment, recoveredByDepartment,
                                activeCasesByDepartment, todayCasesByDepartment, todayDeathCasesByDepartment)
                );
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
            stringMap.put("isDepartment", "NO");
        } else {
            String startMessage = temp.substring(0, indexSpace);
            String department = temp.substring(indexSpace + 1);

            List<DepartmentDto> departmentDtoList = departmentBl.findAllDepartmentsBySimilar(department);
            String departmentDatabase = departmentDtoList.get(0).getDepartment();

            stringMap.put("starMessage", startMessage);
            stringMap.put("department", departmentDatabase);
            stringMap.put("isDepartment", "SI");
        }

        return stringMap;
    }
}
