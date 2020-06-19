package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;
import ucb.edu.bo.sabicovid19.domain.BiDepartment;

import java.util.List;

@Data
public class DepartmentDto {
    private Integer departmentId;
    private String department;

    private List<ProvinceDto> biProvinceList;

    public DepartmentDto(BiDepartment department) {
        this.departmentId = department.getDepartmentId();
        this.department = department.getDepartment();
    }
}
