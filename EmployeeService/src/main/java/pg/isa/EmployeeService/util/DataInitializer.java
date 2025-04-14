package pg.isa.EmployeeService.util;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pg.isa.EmployeeService.entity.Department;
import pg.isa.EmployeeService.entity.Employee;
import pg.isa.EmployeeService.repository.DepartmentRepo;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final DepartmentRepo departmentRepo;

    @PostConstruct
    public void createData() {
        if (departmentRepo.count() > 0) {
            return;
        }

        var departments = List.of(
                Department.builder().id(UUID.fromString("48d8f121-89a1-4ae4-a5ff-12d477afd6d5")).build(),
                Department.builder().id(UUID.fromString("2dde3c32-f916-4c4c-9278-8666f99047d1")).build(),
                Department.builder().id(UUID.fromString("820bb767-794a-4cf8-afdb-d3d6de5c6ed0")).build(),
                Department.builder().id(UUID.fromString("ecae1096-f236-452b-9ad1-9d156d7ba523")).build()
        );

        Employee.inMemoryBuilder().department(departments.get(0)).name("John").salary(2000).build();
        Employee.inMemoryBuilder().department(departments.get(0)).name("Sherlock").salary(2500).build();
        Employee.inMemoryBuilder().department(departments.get(1)).name("Jon").salary(13000).build();
        Employee.inMemoryBuilder().department(departments.get(1)).name("Daenerys").salary(30000).build();
        Employee.inMemoryBuilder().department(departments.get(2)).name("Tyrion").salary(20000).build();
        Employee.inMemoryBuilder().department(departments.get(2)).name("Arya").salary(15000).build();
        Employee.inMemoryBuilder().department(departments.get(3)).name("Samwell").salary(10000).build();
        Employee.inMemoryBuilder().department(departments.get(3)).name("Jaime").salary(50000).build();


        departmentRepo.saveAll(departments);
    }
}
