package org.leedae.bootboard.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi restApi(){
        return GroupedOpenApi.builder()
                .pathsToMatch("/api/**")  // 간단한 경로 패턴 사용
                .group("REST API")
                .build();
    }

    @Bean
    public GroupedOpenApi commonApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/**")       // 경로 패턴을 변경
                .pathsToExclude("/api/**") // 제외할 경로 설정 수정
                .group("COMMON API")
                .build();
    }
}
