package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;
import ucb.edu.bo.sabicovid19.domain.*;

import java.util.Date;

@Data
public class CaseDto {
    private Integer caseId;
    private Integer age;
    private Date updateDate;
    private String district;
    private String zone;

    private GenderDto ganderId;
    private MedicalConditionDto medCondId;
    private MunicipalityDto municipallyId;
    private OriginContagionDto oriContgId;

    public CaseDto(BiCase biCase) {
        this.caseId = biCase.getCaseId();
        this.age = biCase.getAge();
        this.updateDate = biCase.getUpdateDate();
        this.district = biCase.getDistrict();
        this.zone = biCase.getZone();
    }
}
