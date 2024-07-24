package com.example.scentbirdproblem.gameplay.dto.request;

import com.example.scentbirdproblem.gameplay.movement.Movement;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
public class MovementCommitRequestDto {
    @NotNull
    UUID id;

    public MovementCommitRequestDto(Movement movement) {
        this.id = movement.getId();
    }
}
