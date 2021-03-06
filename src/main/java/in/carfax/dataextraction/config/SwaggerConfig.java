package in.carfax.dataextraction.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
@ConditionalOnWebApplication
public class SwaggerConfig {

    @Bean
    public Docket productShopApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("(?!/error).+")).paths(PathSelectors.regex("(?!/internal).+"))
                .build();
    }

    private ApiInfo apiInfo() {
        final Contact contact = new Contact("", "", "phani_kiran85@yahoo.com");
        return new ApiInfoBuilder()
                .title("Data Extraction Service API")
                .description("API's for anything and everything related data extraction from file like vin, make.")
                .contact(contact)
                .build();
    }
}