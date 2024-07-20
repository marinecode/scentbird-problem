package com.example.scentbirdproblem.gameplay.engine;

import com.example.scentbirdproblem.gameplay.GameField;
import com.example.scentbirdproblem.gameplay.GameFieldVisualizer;
import com.example.scentbirdproblem.gameplay.GameStatus;
import com.example.scentbirdproblem.role.Role;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {
    private GameField gameField = new GameField();

    public String getVisualization() {
        return GameFieldVisualizer.getView(gameField);
    }

    public Role getWinner() {
        return gameField.getWinner();
    }

    public GameStatus getStatus() {
        return gameField.getStatus();
    }
}
