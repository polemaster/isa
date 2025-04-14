package pg.isa.EmployeeService.dto.employee.mapper;

import org.springframework.stereotype.Component;
import pg.isa.EmployeeService.dto.employee.EmployeeDTO;
import pg.isa.EmployeeService.entity.Employee;

import java.util.function.Function;

@Component
public class EmployeeDTOMapper implements Function<Employee, EmployeeDTO> {
    @Override
    public EmployeeDTO apply(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getSalary(),
                employee.getDepartment().getId()
        );
    }
}
