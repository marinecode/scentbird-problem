package com.example.scentbirdproblem.gameplay.dto.request;

import com.example.scentbirdproblem.gameplay.movement.Movement;
import com.example.scentbirdproblem.role.Role;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

import static com.example.scentbirdproblem.constant.GamePlayConstants.FIELD_SIZE;

public class MovementPrepareRequestDto {
    @NotNull
    UUID id;
    @Min(0)
    @Max(FIELD_SIZE - 1)
    int x;
    @Min(0)
    @Max(FIELD_SIZE - 1)
    int y;
    @NotNull
    Role role;

    public MovementPrepareRequestDto(Movement movement) {
        this.id = movement.getId();
        this.x = movement.getX();
        this.y = movement.getY();
        this.role = movement.getRole();
    }
}