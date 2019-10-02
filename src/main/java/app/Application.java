package app;

import app.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.awt.*;

@SpringBootApplication
@EnableJpaRepositories
@Import(WebConfig.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}

