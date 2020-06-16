package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;
import ucb.edu.bo.sabicovid19.domain.BiPerson;

import java.util.List;

@Data
public class PersonDto {
    private Integer personId;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String firstSurname;
    private String secondSurname;
    private String thirdSurname;

    private List<UserDto> userDtoList;

    public PersonDto(BiPerson biPerson) {
        this.personId = biPerson.getPersonId();
        this.firstName = biPerson.getFirstName();
        this.secondName = biPerson.getSecondName();
        this.thirdName = biPerson.getThirdName();
        this.firstSurname = biPerson.getFirstSurname();
        this.secondSurname = biPerson.getSecondSurname();
        this.thirdSurname =  biPerson.getThirdSurname();
    }
}
