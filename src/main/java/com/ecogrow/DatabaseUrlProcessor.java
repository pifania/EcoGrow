package com.ecogrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUrlProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String databaseUrl = System.getenv("DATABASE_URL");

        if (databaseUrl == null || databaseUrl.isEmpty()) {
            return; // Sem DATABASE_URL, usa os valores padrão do application.properties
        }

        try {
            URI dbUri = new URI(databaseUrl);
            String userInfo = dbUri.getUserInfo();

            if (userInfo == null || !userInfo.contains(":")) {
                return;
            }

            String username = userInfo.split(":")[0];
            String password = userInfo.substring(userInfo.indexOf(':') + 1);
            String port = dbUri.getPort() == -1 ? "5432" : String.valueOf(dbUri.getPort());
            String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + port + dbUri.getPath();

            Map<String, Object> props = new HashMap<>();
            props.put("spring.datasource.url", jdbcUrl);
            props.put("spring.datasource.username", username);
            props.put("spring.datasource.password", password);
            props.put("spring.datasource.driver-class-name", "org.postgresql.Driver");

            environment.getPropertySources().addFirst(new MapPropertySource("renderDatasource", props));

        } catch (URISyntaxException e) {
            throw new RuntimeException("Falha ao parsear DATABASE_URL: " + databaseUrl, e);
        }
    }
}
