package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private Integer departmentId;
    private String department;

    private List<ProvinceDto> biProvinceList;
}
