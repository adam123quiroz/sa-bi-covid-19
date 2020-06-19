package ucb.edu.bo.sabicovid19.bl;

import org.springframework.stereotype.Service;
import ucb.edu.bo.sabicovid19.dao.BiDepartmentRepository;
import ucb.edu.bo.sabicovid19.dto.DepartmentDto;
import ucb.edu.bo.sabicovid19.enums.Status;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentBl {
    final BiDepartmentRepository biDepartmentRepository;

    public DepartmentBl(BiDepartmentRepository biDepartmentRepository) {
        this.biDepartmentRepository = biDepartmentRepository;
    }

    public List<DepartmentDto> findAllDepartmentsBySimilar(String similar) {
        List<DepartmentDto> departmentDtoArrayList = new ArrayList<>();
        this.biDepartmentRepository.findAllDepartmentsBySimilar(
                similar,
                Status.ACTIVE.getStatus()
        ).forEach(biDepartment -> {
            departmentDtoArrayList.add(new DepartmentDto(biDepartment));
        });
        return departmentDtoArrayList;
    }

}
