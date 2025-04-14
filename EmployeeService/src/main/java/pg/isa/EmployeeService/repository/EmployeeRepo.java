package pg.isa.EmployeeService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.isa.EmployeeService.entity.Department;
import pg.isa.EmployeeService.entity.Employee;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
    List<Employee> findByDepartment(Department department);

    List<Employee> findByNameIgnoreCase(String name);

    List<Employee> findByNameIgnoreCaseAndDepartment(String name, Department department);
}
