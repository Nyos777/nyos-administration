package org.example.smartdeltatest.config.keycloak;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class KeyCloakProperties {
    @Value("${keycloak.url}")
    private String url;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.grant-type}")
    private String grantType;

    @Value("${keycloak.username}")
    private String username;

    @Value("${keycloak.password}")
    private String password;
}
