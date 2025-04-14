package pg.isa.EmployeeService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pg.isa.EmployeeService.dto.employee.EmployeeCreationRequest;
import pg.isa.EmployeeService.dto.employee.EmployeeDTO;
import pg.isa.EmployeeService.dto.employee.EmployeeUpdateRequest;
import pg.isa.EmployeeService.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/employees/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO getEmployee(@PathVariable UUID uuid) {
        return employeeService.getByID(uuid);
    }

    @GetMapping("/departments/{uuid}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> getEmployeesFromDepartment(@PathVariable UUID uuid) {
        return employeeService.getAllFromDepartment(uuid);
    }

    @PostMapping("/departments/{uuid}/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createEmployee(@PathVariable UUID uuid, @RequestBody EmployeeCreationRequest request) {
        return employeeService.create(uuid, request);
    }

    @PatchMapping("/employees/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO updateEmployee(@PathVariable UUID uuid, @RequestBody EmployeeUpdateRequest request) {
        return employeeService.update(uuid, request);
    }

    @DeleteMapping("/employees/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable UUID uuid) {
        employeeService.delete(uuid);
    }

}
