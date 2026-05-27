package com.ecogrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class EcoGrowApplication {
    public static void main(String[] args) {
        String databaseUrl = System.getenv("DATABASE_URL");
        System.out.println("=== ECOGROW INITIALIZATION ===");
        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            System.out.println("DATABASE_URL encontrada: " + databaseUrl.replaceAll(":[^:@]+@", ":****@")); // Oculta senha para segurança
            if (databaseUrl.startsWith("postgres")) {
                try {
                    URI dbUri = new URI(databaseUrl);
                    String userInfo = dbUri.getUserInfo();
                    if (userInfo != null && userInfo.contains(":")) {
                        String username = userInfo.split(":")[0];
                        String password = userInfo.split(":")[1];
                        String port = dbUri.getPort() == -1 ? "5432" : String.valueOf(dbUri.getPort());
                        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + port + dbUri.getPath();
                        
                        System.out.println("JDBC URL construída: " + dbUrl);
                        System.out.println("JDBC Username: " + username);
                        
                        System.setProperty("JDBC_DATABASE_URL", dbUrl);
                        System.setProperty("JDBC_DATABASE_USERNAME", username);
                        System.setProperty("JDBC_DATABASE_PASSWORD", password);
                    } else {
                        System.out.println("ERRO: Formato de userInfo inválido na DATABASE_URL.");
                    }
                } catch (URISyntaxException | NullPointerException e) {
                    System.out.println("ERRO ao parsear DATABASE_URL: " + e.getMessage());
                }
            } else {
                System.out.println("AVISO: DATABASE_URL não começa com 'postgres'. Ignorando parsing dinâmico.");
            }
        } else {
            System.out.println("AVISO: DATABASE_URL não encontrada no ambiente (nula ou vazia).");
        }
        System.out.println("==============================");
        SpringApplication.run(EcoGrowApplication.class, args);
    }
}
