package pg.isa.DepartmentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.isa.DepartmentService.entity.Department;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, UUID> {
    Optional<Department> findById(UUID id);
}
