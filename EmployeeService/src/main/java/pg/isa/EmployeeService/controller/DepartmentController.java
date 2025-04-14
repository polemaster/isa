package pg.isa.EmployeeService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pg.isa.EmployeeService.service.DepartmentService;

import java.util.UUID;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping("/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDepartment(@RequestBody UUID department_id) {
        departmentService.create(department_id);
    }

    @DeleteMapping("/departments/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable UUID uuid) {
        departmentService.delete(uuid);
    }
}
