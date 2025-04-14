package pg.isa.EmployeeService.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pg.isa.EmployeeService.dto.employee.EmployeeCreationRequest;
import pg.isa.EmployeeService.dto.employee.EmployeeDTO;
import pg.isa.EmployeeService.dto.employee.EmployeeUpdateRequest;
import pg.isa.EmployeeService.dto.employee.mapper.EmployeeCreationRequestMapper;
import pg.isa.EmployeeService.dto.employee.mapper.EmployeeDTOMapper;
import pg.isa.EmployeeService.entity.Department;
import pg.isa.EmployeeService.entity.Employee;
import pg.isa.EmployeeService.repository.DepartmentRepo;
import pg.isa.EmployeeService.repository.EmployeeRepo;
import pg.isa.EmployeeService.util.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeeCreationRequestMapper employeeCreationRequestMapper;

    public EmployeeService(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo, EmployeeDTOMapper employeeDTOMapper, EmployeeCreationRequestMapper employeeCreationRequestMapper) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.employeeDTOMapper = employeeDTOMapper;
        this.employeeCreationRequestMapper = employeeCreationRequestMapper;
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepo.findAll()
                .stream()
                .map(employeeDTOMapper)
                .toList();
    }

    public EmployeeDTO getByID(UUID id) {
        return employeeRepo.findById(id)
                .map(employeeDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    public List<Employee> getByDepartment(Department department) {
        return employeeRepo.findByDepartment(department);
    }

    public List<EmployeeDTO> getAllFromDepartment(UUID uuid) {
        Department department = departmentRepo
                .findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", uuid));

        return employeeRepo.findByDepartment(department)
                .stream()
                .map(employeeDTOMapper)
                .toList();
    }

    public List<Employee> getByName(String name) {
        return employeeRepo.findByNameIgnoreCase(name);
    }

    public List<Employee> getByNameAndDepartment(String name, Department department) {
        return employeeRepo.findByNameIgnoreCaseAndDepartment(name, department);
    }

    public EmployeeDTO create(UUID uuid, EmployeeCreationRequest request) {
        Department department = departmentRepo.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", uuid));
        Employee employee = employeeCreationRequestMapper.apply(request);
        employee.setDepartment(department);

        return employeeDTOMapper.apply(employeeRepo.save(employee));
    }

    public EmployeeDTO update(UUID uuid, EmployeeUpdateRequest request) {
        Employee employee = employeeRepo.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", uuid));

        Optional<UUID> department_id = request.department_id();
        Department department = department_id
                .map(id -> departmentRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id)))
                .orElse(null);

        request.name().ifPresent(employee::setName);
        request.salary().ifPresent(employee::setSalary);
        request.department_id().ifPresent(id -> {
            employee.setDepartment(department);
        });

        return employeeDTOMapper.apply(employee);
    }

    public void delete(UUID id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employeeRepo.delete(employee);
    }
}
