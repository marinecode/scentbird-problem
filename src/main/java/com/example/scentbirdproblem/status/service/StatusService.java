package com.example.scentbirdproblem.status.service;

import com.example.scentbirdproblem.gameplay.GameStatus;
import com.example.scentbirdproblem.gameplay.engine.GameEngine;
import com.example.scentbirdproblem.gameplay.movement.Movement;
import com.example.scentbirdproblem.gameplay.movement.MovementStorage;
import com.example.scentbirdproblem.status.ConsistencyStatus;
import com.example.scentbirdproblem.status.dto.response.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final GameEngine gameEngine;
    private final MovementStorage movementStorage;

    public StatusResponseDto getStatus() {
        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setVisualization(gameEngine.getVisualization());
        statusResponseDto.setStatus(gameEngine.getStatus());
        statusResponseDto.setWinner(gameEngine.getWinner());
        statusResponseDto.setConsistencyStatus(getConsistencyStatus());
        return statusResponseDto;
    }

    private ConsistencyStatus getConsistencyStatus() {
        ConsistencyStatus consistencyStatus = ConsistencyStatus.INCONSISTENT_STATE;
        if (gameEngine.getStatus() == GameStatus.NOT_STARTED) {
            consistencyStatus = ConsistencyStatus.CONSISTENT_STATE;
        } else if (!movementStorage.isEmpty()) {
            Movement lastMovement = movementStorage.getLastMovement();
            if (lastMovement.isCommitted()) {
                consistencyStatus = ConsistencyStatus.CONSISTENT_STATE;
            }
        }

        return consistencyStatus;
    }


}
