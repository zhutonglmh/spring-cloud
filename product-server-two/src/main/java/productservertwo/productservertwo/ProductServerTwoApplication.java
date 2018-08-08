package productservertwo.productservertwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient  //声明这是一个服务提供者
public class ProductServerTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServerTwoApplication.class, args);
	}
}
