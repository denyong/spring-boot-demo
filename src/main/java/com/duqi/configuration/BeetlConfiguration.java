package com.duqi.configuration;

import java.io.IOException;
import java.util.Properties;

import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.FileResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 模板引擎配置
 */
@Configuration
public class BeetlConfiguration {

    @Bean
    GroupTemplate groupTemplate() throws IOException {
        FileResourceLoader resourceLoader = new FileResourceLoader("/");
        Properties beetlProperties = new Properties();
        beetlProperties.load(new ClassPathResource("beetl.properties").getInputStream());
        org.beetl.core.Configuration cfg = new org.beetl.core.Configuration(beetlProperties);
        return new GroupTemplate(resourceLoader, cfg);
    }
}
