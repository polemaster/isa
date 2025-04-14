package pg.isa.EmployeeService.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pg.isa.EmployeeService.entity.Department;
import pg.isa.EmployeeService.repository.DepartmentRepo;
import pg.isa.EmployeeService.util.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    public Department getByID(UUID id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }

    public void create(UUID department_id) {
        departmentRepo.save(Department.builder().id(department_id).build());
    }


    public void delete(UUID id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        departmentRepo.delete(department);
    }
}
