package com.snap.practice.github;

//import lombok.extern.slf4j.Slf4j;

import com.snap.practice.github.model.RepositoryModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


//@Slf4j
@Component
public class GithubComponent {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(GithubComponent.class);

    @Value("${properties.github.url}")
    private String GITHUB_API_URL;

    @Value("${properties.github.user-repository-uri}")
    private String GET_USER_REPOSITORIES;

    private final RestTemplate restTemplate;

    public GithubComponent(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Set<RepositoryModel> getUserRepositories(final String username) {
        Objects.requireNonNull(username);
        Set<RepositoryModel> repositories = new HashSet<>();
        try {
            final ResponseEntity<Set<RepositoryModel>> response = restTemplate.exchange(
                    GITHUB_API_URL + GET_USER_REPOSITORIES,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Set<RepositoryModel>>() {
                    },
                    username);
            repositories.addAll(response.getBody());

        } catch (Exception e) {
            log.error("getUserRepositories", e);
        }
        return repositories;
    }
}
