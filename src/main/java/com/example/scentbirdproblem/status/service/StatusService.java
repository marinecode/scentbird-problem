package com.example.scentbirdproblem.status.service;

import com.example.scentbirdproblem.gameplay.engine.GameEngine;
import com.example.scentbirdproblem.status.dto.response.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final GameEngine gameEngine;

    public StatusResponseDto getStatus() {
        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setVisualization(gameEngine.getVisualization());
        statusResponseDto.setStatus(gameEngine.getStatus());
        statusResponseDto.setWinner(gameEngine.getWinner());
        return statusResponseDto;
    }

}
