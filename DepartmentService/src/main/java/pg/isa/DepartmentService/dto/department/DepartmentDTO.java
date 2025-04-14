package pg.isa.DepartmentService.dto.department;

import java.util.UUID;

public record DepartmentDTO (UUID id, String name, char building) {

}
