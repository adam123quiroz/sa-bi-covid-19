package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CasePostDto {
    private Integer age;
    private Date updateDate;
    private String district;
    private String zone;

    private Integer ganderId;
    private Integer medCondId;
    private Integer municipallyId;
    private Integer oriContgId;
}
