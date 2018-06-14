package in.carfax.dataextraction;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2
public class DataExtractionApplication {

  public static void main(String[] args) {
    SpringApplication.run(DataExtractionApplication.class, args);
  }
}
