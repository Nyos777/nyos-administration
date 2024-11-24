package org.example.smartdeltatest.config.keycloak;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KeyCloakConfig {

    private final KeyCloakProperties keyCloakProperties;


    @Bean
    public Keycloak keycloak() {
        log.info("Initializing Keycloak instance: realm = {}", keyCloakProperties.getRealm());
        return KeycloakBuilder.builder()
                .serverUrl(keyCloakProperties.getUrl())
                .realm(keyCloakProperties.getRealm())
                .clientSecret(keyCloakProperties.getClientSecret())
                .clientId(keyCloakProperties.getClientId())
                .grantType(keyCloakProperties.getGrantType())
                .username(keyCloakProperties.getUsername())
                .password(keyCloakProperties.getPassword())
                .build();
    }
}
