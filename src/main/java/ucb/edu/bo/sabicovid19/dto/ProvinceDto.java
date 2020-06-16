package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProvinceDto {
    private Integer provinceId;
    private String province;

    private DepartmentDto departmentId;
    private List<MunicipalityDto> biMunicipalityList;
}
