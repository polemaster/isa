package pg.isa.DepartmentService.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pg.isa.DepartmentService.entity.Department;
import pg.isa.DepartmentService.repository.DepartmentRepo;

import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer {
    private final DepartmentRepo departmentRepo;

    public DataInitializer(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @PostConstruct
    public void createData() {
        if (departmentRepo.count() > 0) {
            return;
        }

        var departments = List.of(
                Department.builder()
                        .id(UUID.fromString("48d8f121-89a1-4ae4-a5ff-12d477afd6d5"))
                        .name("HR")
                        .building('A')
                        .build(),
                Department.builder()
                        .id(UUID.fromString("2dde3c32-f916-4c4c-9278-8666f99047d1"))
                        .name("IT")
                        .building('B')
                        .build(),
                Department.builder()
                        .id(UUID.fromString("820bb767-794a-4cf8-afdb-d3d6de5c6ed0"))
                        .name("IT 2")
                        .building('C')
                        .build(),
                Department.builder()
                        .id(UUID.fromString("ecae1096-f236-452b-9ad1-9d156d7ba523"))
                        .name("Management")
                        .building('A')
                        .build()
        );

        departmentRepo.saveAll(departments);
    }
}
