package com.ecogrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class EcoGrowApplication {
    public static void main(String[] args) {
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl != null && !databaseUrl.isEmpty() && databaseUrl.startsWith("postgres")) {
            try {
                URI dbUri = new URI(databaseUrl);
                String userInfo = dbUri.getUserInfo();
                if (userInfo != null && userInfo.contains(":")) {
                    String username = userInfo.split(":")[0];
                    String password = userInfo.split(":")[1];
                    String port = dbUri.getPort() == -1 ? "5432" : String.valueOf(dbUri.getPort());
                    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + port + dbUri.getPath();
                    
                    System.setProperty("JDBC_DATABASE_URL", dbUrl);
                    System.setProperty("JDBC_DATABASE_USERNAME", username);
                    System.setProperty("JDBC_DATABASE_PASSWORD", password);
                }
            } catch (URISyntaxException | NullPointerException e) {
                // Fallback silencioso para as propriedades padrão
            }
        }
        SpringApplication.run(EcoGrowApplication.class, args);
    }
}
