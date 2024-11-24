package org.example.smartdeltatest.service.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smartdeltatest.config.keycloak.KeyCloakProperties;
import org.example.smartdeltatest.model.dto.UserCreateDTO;
import org.example.smartdeltatest.model.dto.UserLoginDTO;
import org.example.smartdeltatest.model.dto.UserTokenDTO;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeyCloakService {

    private final KeyCloakProperties keyCloakProperties;

    private final RestTemplate restTemplate;

    private final Keycloak keycloak;

    public UserRepresentation createUser(UserCreateDTO userCreateDTO){

        UserRepresentation newUser = new UserRepresentation();
        newUser.setEmail(userCreateDTO.getEmail());
        newUser.setEmailVerified(true);
        newUser.setUsername(userCreateDTO.getUsername());
        newUser.setFirstName(userCreateDTO.getFirstName());
        newUser.setLastName(userCreateDTO.getLastName());
        newUser.setEnabled(true);

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(userCreateDTO.getPassword());
        credentials.setTemporary(false);

        newUser.setCredentials(List.of(credentials));

        try (var response = keycloak.realm(keyCloakProperties.getRealm()).users().create(newUser)){

            if(response.getStatus() != HttpStatus.CREATED.value()){
                log.error("Error on creating user, status : {}", response.getStatus());
                throw new RuntimeException("Failed to create user in keyCloak, status = " + response.getStatus());
            }

            List<UserRepresentation> userList = keycloak.realm(keyCloakProperties.getRealm())
                    .users()
                    .search(userCreateDTO.getUsername());

            return userList.get(0);
        }
    }

    public UserTokenDTO signIn(UserLoginDTO userLoginDTO){

        String tokenEndpoint = keyCloakProperties.getUrl() + "/realms/" + keyCloakProperties.getRealm() + "/protocol/openid-connect/token";

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", keyCloakProperties.getGrantType());
        form.add("client_id", keyCloakProperties.getClientId());
        form.add("client_secret", keyCloakProperties.getClientSecret());
        form.add("username", userLoginDTO.getUsername());
        form.add("password", userLoginDTO.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenEndpoint, new HttpEntity<>(form, headers), Map.class);

        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to sign in, status : " + response.getStatusCode());
        }

        Map<String, Object> responseBody = response.getBody();

        return UserTokenDTO.builder()
                .token(responseBody.get("access_token").toString())
                .refreshToken(responseBody.get("refresh_token").toString())
                .build();
    }
}
