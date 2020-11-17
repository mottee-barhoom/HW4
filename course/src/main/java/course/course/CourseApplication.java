package course.course;

import java.io.FileReader;
import java.io.IOException;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient

public class CourseApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @Bean
    public Docket api() throws IOException, org.codehaus.plexus.util.xml.pull.XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        org.apache.maven.model.Model model = reader.read(new FileReader("pom.xml"));
        ApiInfoBuilder builder = new ApiInfoBuilder()
                .title("Person Service Api Documentation")
                .description("Documentation automatically generated")
                .version(model.getVersion());
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("course.course"))
                .paths(PathSelectors.any()).build()
                .apiInfo(builder.build());
    }

}
