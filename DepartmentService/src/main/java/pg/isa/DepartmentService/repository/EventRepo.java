package pg.isa.DepartmentService.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class EventRepo {
    @Qualifier("employee-service")
    private final RestTemplate employeeServiceRestTemplate;

    public EventRepo(RestTemplate employeeServiceRestTemplate) {
        this.employeeServiceRestTemplate = employeeServiceRestTemplate;
    }

    public void sendDepartmentCreationEvent(UUID department_id) {
        employeeServiceRestTemplate.postForObject("/departments", department_id, Void.class);
    }

    public void sendDepartmentDeletionEvent(UUID department_id) {
        employeeServiceRestTemplate.delete("/departments/" + department_id);
    }

}
