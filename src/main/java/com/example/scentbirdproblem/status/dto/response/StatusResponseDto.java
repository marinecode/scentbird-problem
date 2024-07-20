package com.example.scentbirdproblem.status.dto.response;

import com.example.scentbirdproblem.gameplay.GameStatus;
import com.example.scentbirdproblem.role.Role;
import lombok.Data;

@Data
public class StatusResponseDto {

    private String visualization;
    private GameStatus status;
    private Role winner;
}
