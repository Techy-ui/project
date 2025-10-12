package com.example.codecare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class CodeCareApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CodeCareApplication.class);

        app.addListeners((ApplicationListener<ApplicationEnvironmentPreparedEvent>) event -> {
            ConfigurableEnvironment env = event.getEnvironment();
            String dbUrl = env.getProperty("DATABASE_URL");
            if (dbUrl != null && !dbUrl.startsWith("jdbc:")) {
                System.setProperty("spring.datasource.url", "jdbc:" + dbUrl);
                System.out.println("✅ Converted DATABASE_URL to JDBC URL: jdbc:" + dbUrl);
            }
        });

        app.run(args);
    }
}
