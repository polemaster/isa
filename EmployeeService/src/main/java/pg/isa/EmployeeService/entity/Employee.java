package pg.isa.EmployeeService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @ToString.Exclude
    @Builder.Default
    @Column(name = "employee_id")
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    public static EmployeeBuilder inMemoryBuilder() {
        return new InMemoryBuilder();
    }

    public static class InMemoryBuilder extends EmployeeBuilder {
        @Override
        public Employee build() {
            if (super.department == null) {
                throw new IllegalStateException("Department not set");
            }

            var employee = super.build();
            super.department.getEmployees().add(employee);
            return employee;
        }
    }
}
