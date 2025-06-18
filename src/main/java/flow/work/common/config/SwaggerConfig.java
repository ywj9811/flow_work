package flow.work.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi extensionApi() {
        return GroupedOpenApi.builder()
                .group("extension-api")
                .pathsToMatch("/**")
                .build();
    }
}
