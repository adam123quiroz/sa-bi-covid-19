package ucb.edu.bo.sabicovid19.model;

import lombok.Data;

@Data
public class MessageReportModel {
    private String place;
    private Integer totalCases;
    private Integer totalDeath;
    private Integer totalRecovered;
    private Integer totalActiveCases;

    private Integer totalCasesToday;
    private Integer totalDeathToday;
}
