package pg.isa.DepartmentService.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pg.isa.DepartmentService.dto.department.DepartmentCreationRequest;
import pg.isa.DepartmentService.dto.department.DepartmentDTO;
import pg.isa.DepartmentService.dto.department.DepartmentUpdateRequest;
import pg.isa.DepartmentService.dto.department.mapper.DepartmentCreationRequestMapper;
import pg.isa.DepartmentService.dto.department.mapper.DepartmentDTOMapper;
import pg.isa.DepartmentService.entity.Department;
import pg.isa.DepartmentService.repository.DepartmentRepo;
import pg.isa.DepartmentService.repository.EventRepo;
import pg.isa.DepartmentService.util.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final EventRepo eventRepo;
    private final DepartmentDTOMapper departmentDTOMapper;
    private final DepartmentCreationRequestMapper departmentCreationRequestMapper;

    public DepartmentService(DepartmentRepo departmentRepo, EventRepo eventRepo, DepartmentDTOMapper departmentDTOMapper, DepartmentCreationRequestMapper departmentCreationRequestMapper) {
        this.departmentRepo = departmentRepo;
        this.eventRepo = eventRepo;
        this.departmentDTOMapper = departmentDTOMapper;
        this.departmentCreationRequestMapper = departmentCreationRequestMapper;
    }


    public List<DepartmentDTO> getAll() {
        return departmentRepo.findAll()
                .stream()
                .map(departmentDTOMapper)
                .toList();
    }

    public DepartmentDTO getByID(UUID id) {
        return departmentRepo.findById(id)
                .map(departmentDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }


    public DepartmentDTO create(DepartmentCreationRequest request) {
        Department department = departmentCreationRequestMapper.apply(request);
        departmentRepo.save(department);

        eventRepo.sendDepartmentCreationEvent(department.getId());

        return departmentDTOMapper.apply(department);
    }

    public DepartmentDTO update(UUID uuid, DepartmentUpdateRequest request) {
        Department department = departmentRepo.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", uuid));

        request.name().ifPresent(department::setName);
        request.building().ifPresent(department::setBuilding);

        return departmentDTOMapper.apply(department);
    }

    public void delete(UUID id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        eventRepo.sendDepartmentDeletionEvent(id);

        departmentRepo.delete(department);
    }
}
