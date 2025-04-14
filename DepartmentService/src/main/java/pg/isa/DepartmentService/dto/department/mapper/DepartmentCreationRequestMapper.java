package pg.isa.DepartmentService.dto.department.mapper;

import org.springframework.stereotype.Component;
import pg.isa.DepartmentService.dto.department.DepartmentCreationRequest;
import pg.isa.DepartmentService.entity.Department;

import java.util.function.Function;

@Component
public class DepartmentCreationRequestMapper implements Function<DepartmentCreationRequest, Department> {
    @Override
    public Department apply(DepartmentCreationRequest departmentCreationRequest) {
        return Department.builder()
                .name(departmentCreationRequest.name())
                .building(departmentCreationRequest.building())
                .build();
    }
}
