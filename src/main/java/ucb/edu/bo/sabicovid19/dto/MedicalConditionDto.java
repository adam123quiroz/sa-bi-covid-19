package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;
import ucb.edu.bo.sabicovid19.domain.BiMedicalCondition;

@Data
public class MedicalConditionDto {
    private Integer medCondId;
    private String medicalCondition;

    public MedicalConditionDto(BiMedicalCondition medicalCondition) {
        this.medCondId = medicalCondition.getMedCondId();
        this.medicalCondition = medicalCondition.getMedicalCondition();
    }
}
