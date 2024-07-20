package com.example.scentbirdproblem.opponent.connector;

import com.example.scentbirdproblem.constant.URL;
import com.example.scentbirdproblem.gameplay.dto.request.MovementCommitRequestDto;
import com.example.scentbirdproblem.gameplay.dto.request.MovementPrepareRequestDto;
import com.example.scentbirdproblem.gameplay.dto.response.MovementCommitResponseDto;
import com.example.scentbirdproblem.gameplay.dto.response.MovementPrepareResponseDto;
import com.example.scentbirdproblem.gameplay.movement.Movement;
import com.example.scentbirdproblem.role.dto.response.RoleResponseDto;
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
    private final RestTemplate restTemplate = new RestTemplate(); //TODO try to use WebClient

    @Retryable(backoff = @Backoff(delay = 5000), maxAttempts = Integer.MAX_VALUE, listeners = "opponentRoleRetryListener")
    //TODO Rethink the retry configurations
    public RoleResponseDto getOpponentRole() {
        String opponentRoleUrl = opponentBaseUrl + URL.ROLE_ROOT + "?suggestion=";
        return restTemplate.getForEntity(opponentRoleUrl + roleSelectionService.getMySuggestion(), RoleResponseDto.class).getBody();
    }

    @Retryable(backoff = @Backoff(delay = 5000), maxAttempts = Integer.MAX_VALUE)
    //TODO Rethink the retry configurations
    public void prepareMovement(Movement movement) {
        MovementPrepareRequestDto requestDto = new MovementPrepareRequestDto(movement);
        String movementPrepareUrl = opponentBaseUrl + URL.MOVEMENT_ROOT + URL.MOVEMENT_PREPARE;
        restTemplate.postForEntity(movementPrepareUrl, requestDto, MovementPrepareResponseDto.class);
    }

    @Retryable(backoff = @Backoff(delay = 5000), maxAttempts = Integer.MAX_VALUE)
    //TODO Rethink the retry configurations
    public void commitMovement(Movement movement) {
        String movementCommitUrl = opponentBaseUrl + URL.MOVEMENT_ROOT + URL.MOVEMENT_COMMIT;
        MovementCommitRequestDto requestDto = new MovementCommitRequestDto(movement);
        restTemplate.postForEntity(movementCommitUrl, requestDto, MovementCommitResponseDto.class);
    }

}
