package pg.isa.DepartmentService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pg.isa.DepartmentService.dto.department.DepartmentCreationRequest;
import pg.isa.DepartmentService.dto.department.DepartmentDTO;
import pg.isa.DepartmentService.dto.department.DepartmentUpdateRequest;
import pg.isa.DepartmentService.service.DepartmentService;

import java.util.List;
import java.util.UUID;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentDTO> getDepartments() {
        return departmentService.getAll();
    }

    @GetMapping("/departments/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentDTO getDepartment(@PathVariable UUID uuid) {
        return departmentService.getByID(uuid);
    }

    @PostMapping("/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDTO createDepartment(@RequestBody DepartmentCreationRequest request) {
        return departmentService.create(request);
    }

    @PatchMapping("/departments/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentDTO updateDepartment(@PathVariable UUID uuid, @RequestBody DepartmentUpdateRequest request) {
        return departmentService.update(uuid, request);
    }

    @DeleteMapping("/departments/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable UUID uuid) {
        departmentService.delete(uuid);
    }
}
