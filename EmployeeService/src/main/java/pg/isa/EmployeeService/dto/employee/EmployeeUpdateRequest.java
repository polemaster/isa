package pg.isa.EmployeeService.dto.employee;

import java.util.Optional;
import java.util.UUID;

public record EmployeeUpdateRequest (Optional<String> name, Optional<Double> salary, Optional<UUID> department_id) {
}
