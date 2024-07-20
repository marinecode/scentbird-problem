package com.example.scentbirdproblem.gameplay.engine;

import com.example.scentbirdproblem.gameplay.GameField;
import com.example.scentbirdproblem.gameplay.GameFieldVisualizer;
import com.example.scentbirdproblem.gameplay.GameStatus;
import com.example.scentbirdproblem.gameplay.MovementStorage;
import com.example.scentbirdproblem.role.Role;
import com.example.scentbirdproblem.role.RoleContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameEngine {
    private GameField gameField = new GameField();
    private MovementStorage movementStorage = new MovementStorage();
    private final RoleContainer roleContainer;

    public String getVisualization() {
        return GameFieldVisualizer.getView(gameField);
    }


    public void startGame() {
        Role myRole = roleContainer.getMyRole();
    }

    public Role getWinner() {
        return gameField.getWinner();
    }

    public GameStatus getStatus() {
        return gameField.getStatus();
    }
}
