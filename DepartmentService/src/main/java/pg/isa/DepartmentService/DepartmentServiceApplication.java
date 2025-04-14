package pg.isa.DepartmentService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

    @Bean
    @Qualifier("employee-service")
    public RestTemplate employeeServiceRestTemplate(@Value("${gateway.employee-service.url}") String employeeServiceUrl) {
        return new RestTemplateBuilder()
                .rootUri(employeeServiceUrl)
                .build();
    }
}
