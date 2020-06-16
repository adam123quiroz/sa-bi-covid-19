package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;
import ucb.edu.bo.sabicovid19.domain.BiMunicipality;

import java.util.List;

@Data
public class MunicipalityDto {
    private Integer municipallyId;
    private String municipally;

    private List<CaseDto> biCaseList;
    private ProvinceDto provinceId;

    public MunicipalityDto(BiMunicipality municipality) {
        this.municipallyId = municipality.getMunicipallyId();
        this.municipally = municipality.getMunicipally();
    }
}
