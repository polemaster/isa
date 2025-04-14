package pg.isa.EmployeeService.dto.employee;

import java.util.UUID;

public record EmployeeDTO (UUID id, String name, double salary, UUID department_id) {
}
