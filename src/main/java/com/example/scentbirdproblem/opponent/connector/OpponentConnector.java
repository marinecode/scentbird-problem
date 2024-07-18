package com.example.scentbirdproblem.opponent.connector;

import com.example.scentbirdproblem.constant.URL;
import com.example.scentbirdproblem.role.response.RoleResponseDto;
import com.example.scentbirdproblem.role.service.RoleSelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OpponentConnector {

    @Value("${opponent.base.url}")
    private String opponentBaseUrl;
    private final RoleSelectionService roleSelectionService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Retryable(backoff = @Backoff(delay = 5000), maxAttempts = Integer.MAX_VALUE, listeners = "retryListener")
    public RoleResponseDto getOpponentRole() {
        String opponentRoleUrl = opponentBaseUrl + URL.ROLE_ROOT + "?suggestion=";
        return restTemplate.getForEntity(opponentRoleUrl + roleSelectionService.getMySuggestion(), RoleResponseDto.class).getBody();
    }

}
