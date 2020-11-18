package io.x16fd16b.assignment03.school.starter;

import io.x16fd16b.assignment03.school.starter.service.SchoolService;
import io.x16fd16b.assignment03.school.starter.service.SchoolServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SchoolAutoConfiguration
 *
 * @author David Liu
 */
@Configuration
@EnableConfigurationProperties(SchoolProperties.class)
public class SchoolAutoConfiguration {
    @Bean
    public School school(SchoolProperties schoolProperties) {
        School school = new School();
        school.setName(schoolProperties.getName());
        school.setKlasses(schoolProperties.getKlasses());
        return school;
    }

    @Bean
    @ConditionalOnProperty(value = "school.enablePrintInfo", matchIfMissing = true)
    public SchoolService schoolService() {
        return new SchoolServiceImpl();
    }
}
