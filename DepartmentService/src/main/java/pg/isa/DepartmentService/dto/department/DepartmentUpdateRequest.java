package pg.isa.DepartmentService.dto.department;

import java.util.Optional;

public record DepartmentUpdateRequest (Optional<String> name, Optional<Character> building) {
}
