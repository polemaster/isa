package pg.isa.EmployeeService.dto.employee.mapper;

import org.springframework.stereotype.Component;
import pg.isa.EmployeeService.dto.employee.EmployeeCreationRequest;
import pg.isa.EmployeeService.entity.Employee;

import java.util.function.Function;

@Component
public class EmployeeCreationRequestMapper implements Function<EmployeeCreationRequest, Employee> {
    @Override
    public Employee apply(EmployeeCreationRequest employeeCreationRequest) {
        return Employee.builder()
                .name(employeeCreationRequest.name())
                .salary(employeeCreationRequest.salary())
                .build();
    }
}
