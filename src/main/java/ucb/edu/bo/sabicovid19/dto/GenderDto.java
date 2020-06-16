package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;
import ucb.edu.bo.sabicovid19.domain.BiGender;

@Data
public class GenderDto {
    private Integer genderId;
    private String gender;

    public GenderDto(BiGender gender) {
        this.genderId = gender.getGenderId();
        this.gender = gender.getGender();
    }
}
