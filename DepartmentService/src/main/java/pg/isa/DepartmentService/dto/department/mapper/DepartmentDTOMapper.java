package pg.isa.DepartmentService.dto.department.mapper;

import org.springframework.stereotype.Component;
import pg.isa.DepartmentService.dto.department.DepartmentDTO;
import pg.isa.DepartmentService.entity.Department;

import java.util.function.Function;

@Component
public class DepartmentDTOMapper implements Function<Department, DepartmentDTO> {
    @Override
    public DepartmentDTO apply(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getBuilding()
        );
    }
}
