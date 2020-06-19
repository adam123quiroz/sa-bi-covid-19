package ucb.edu.bo.sabicovid19.bl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ucb.edu.bo.sabicovid19.dto.DepartmentDto;
import ucb.edu.bo.sabicovid19.enums.MedicalCondition;
import ucb.edu.bo.sabicovid19.model.MessageReportModel;

import java.util.List;

@Slf4j
@Service
public class BotTelegramBl {
    final
    CaseBl caseBl;
    final
    DepartmentBl departmentBl;

    public BotTelegramBl(CaseBl caseBl, DepartmentBl departmentBl) {
        this.caseBl = caseBl;
        this.departmentBl = departmentBl;
    }

    public MessageReportModel findAllCasesBolivia() {
        MessageReportModel messageReportModel = new MessageReportModel();

        Integer totalCases = caseBl.countAllCasesActive();
        Integer deaths = caseBl.countAllByMedCondIdActive(MedicalCondition.Death.getStatus());
        Integer recovered = caseBl.countAllByMedCondIdActive(MedicalCondition.Recovered.getStatus());
        Integer activeCases = caseBl.countAllByMedCondIdActive(MedicalCondition.Confirmed.getStatus());

        Integer todayCases = caseBl.countAllCasesToday();
        Integer todayCasesDeath = caseBl.countAllCasesByMedicalConditionToday(MedicalCondition.Death.getStatus());

        messageReportModel.setPlace("Bolivia");
        messageReportModel.setTotalCases(totalCases);
        messageReportModel.setTotalDeath(deaths);
        messageReportModel.setTotalRecovered(recovered);
        messageReportModel.setTotalActiveCases(activeCases);

        messageReportModel.setTotalCasesToday(todayCases);
        messageReportModel.setTotalDeathToday(todayCasesDeath);
        return messageReportModel;
    }

    public MessageReportModel findAllCasesByDepartment(String department) {
        MessageReportModel messageReportModel = new MessageReportModel();

        List<DepartmentDto> departmentDtoList = departmentBl.findAllDepartmentsBySimilar(department);
        String departmentDatabase = departmentDtoList.get(0).getDepartment();

        Integer totalCasesByDepartment = caseBl.countAllCasesActiveByDepartment(departmentDatabase);
        Integer deathsByDepartment = caseBl.countAllByDepartmentAndMedicalConditionActive(
                departmentDatabase,
                MedicalCondition.Death.getStatus()
        );
        Integer recoveredByDepartment = caseBl.countAllByDepartmentAndMedicalConditionActive(
                departmentDatabase,
                MedicalCondition.Recovered.getStatus()
        );
        Integer activeCasesByDepartment = caseBl.countAllByDepartmentAndMedicalConditionActive(
                departmentDatabase,
                MedicalCondition.Confirmed.getStatus()
        );

        Integer todayCasesByDepartment = caseBl.countAllCasesTodayByDepartment(departmentDatabase);
        Integer todayDeathCasesByDepartment = caseBl.countAllCasesByMedicalConditionToday(departmentDatabase);

        messageReportModel.setPlace(departmentDatabase);
        messageReportModel.setTotalCases(totalCasesByDepartment);
        messageReportModel.setTotalDeath(deathsByDepartment);
        messageReportModel.setTotalRecovered(recoveredByDepartment);
        messageReportModel.setTotalActiveCases(activeCasesByDepartment);

        messageReportModel.setTotalCasesToday(todayCasesByDepartment);
        messageReportModel.setTotalDeathToday(todayDeathCasesByDepartment);

        return messageReportModel;
    }
}
