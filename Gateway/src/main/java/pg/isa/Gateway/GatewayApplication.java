package pg.isa.Gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
    private final String departmentServiceUrl;
    private final String employeeServiceUrl;

    public GatewayApplication(
            @Value("${gateway.department-service.url}") String departmentServiceUrl,
            @Value("${gateway.employee-service.url}") String employeeServiceUrl
    ) {
        this.departmentServiceUrl = departmentServiceUrl;
        this.employeeServiceUrl = employeeServiceUrl;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator storeRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("departments", route -> route
                        .path(
                                "/api/departments",
                                "/api/departments/{uuid}"
                        )
                        .uri(departmentServiceUrl)
                )
                .route("employees", route -> route
                        .path(
                                "/api/employees",
                                "/api/employees/{uuid}",
                                "/api/departments/{uuid}/employees"
                        )
                        .uri(employeeServiceUrl)
                )
                .build();
    }
}
