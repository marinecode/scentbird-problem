package com.example.scentbirdproblem.gameplay.dto.request;

import com.example.scentbirdproblem.gameplay.movement.Movement;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class MovementCommitRequestDto {
    @NotNull
    private UUID id;

    public MovementCommitRequestDto(Movement movement) {
        this.id = movement.getId();
    }
}
