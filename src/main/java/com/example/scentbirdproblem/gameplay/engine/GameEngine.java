package com.example.scentbirdproblem.gameplay.engine;

import com.example.scentbirdproblem.gameplay.GameField;
import com.example.scentbirdproblem.gameplay.GameFieldVisualizer;
import com.example.scentbirdproblem.gameplay.GameStatus;
import com.example.scentbirdproblem.gameplay.movement.Movement;
import com.example.scentbirdproblem.gameplay.movement.service.MovementService;
import com.example.scentbirdproblem.role.Role;
import com.example.scentbirdproblem.role.RoleContainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
@RequiredArgsConstructor
public class GameEngine {
    private final GameField gameField = new GameField();
    private final RoleContainer roleContainer;
    private final MovementService movementService;

    public String getVisualization() {
        return GameFieldVisualizer.getView(gameField);
    }


    public void startGame() {
        if (!roleContainer.isRoleSet()) {
            throw new IllegalStateException("Role is not set");
        } else if (shouldStart()) {
            while (gameField.getStatus() == GameStatus.IN_PROGRESS && isMyTurn()) {
                movementService.prepareMovement(gameField.getField());
                Movement committedMovement = movementService.commitMovement();
                gameField.occupyCell(committedMovement);
            }
        } else {
            log.info("Game started. My role is " + roleContainer.getMyRole() + "Waiting for opponent's move");
        }
    }

    private boolean shouldStart() {
        return roleContainer.getMyRole() == Role.O;
    }

    private boolean isMyTurn() {
        return movementService.whoseTurn() == roleContainer.getMyRole();
    }

    public Role getWinner() {
        return gameField.getWinner();
    }

    public GameStatus getStatus() {
        return gameField.getStatus();
    }
}
