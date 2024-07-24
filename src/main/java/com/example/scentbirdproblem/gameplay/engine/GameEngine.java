package com.example.scentbirdproblem.gameplay.engine;

import com.example.scentbirdproblem.gameplay.GameField;
import com.example.scentbirdproblem.gameplay.GameFieldVisualizer;
import com.example.scentbirdproblem.gameplay.GameStatus;
import com.example.scentbirdproblem.gameplay.movement.Movement;
import com.example.scentbirdproblem.gameplay.movement.service.MyMovementServiceImpl;
import com.example.scentbirdproblem.role.Role;
import com.example.scentbirdproblem.role.RoleContainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log
@Component
@RequiredArgsConstructor
public class GameEngine {
    private final GameField gameField = new GameField();
    private Role whoseTurn = Role.NONE;
    private final RoleContainer roleContainer;
    private final MyMovementServiceImpl myMovementService;

    public String getVisualization() {
        return GameFieldVisualizer.getView(gameField);
    }


    @Scheduled(fixedRate = 10000)
    public void makeMoveInMyTurn() {
        if (gameField.getStatus() == GameStatus.IN_PROGRESS && isMyTurn()) {
            log.info("My turn. Preparing movement");
            myMovementService.suggestMovement(gameField.getField());
            log.info("Movement prepared. Committing");
            Movement committedMovement = myMovementService.commitMovement();
            log.info("Movement committed + " + committedMovement);
            gameField.occupyCell(committedMovement);
            whoseTurn = roleContainer.getOpponentRole();
        } else {
            log.info("Not my turn");
        }
    }

    public void startGame() {
        if (roleContainer.isRoleSet()) {
            whoseTurn = Role.O;
            gameField.startGame();
        }
    }

    public void checkIfMovementValid(Movement opponentMovement) {
        if (gameField.isCellOccupied(opponentMovement)) {
            throw new IllegalArgumentException("Cell is already occupied");
        }
        if (!opponentMovement.getRole().equals(whoseTurn)) {
            throw new IllegalArgumentException("Whose turn " + whoseTurn + ". My role " + roleContainer.getMyRole() + ". Try to prepare the movement with " + opponentMovement.getRole());
        }
    }

    public void occupyCellInOpponentTurn(Movement opponentMovement) {
        gameField.occupyCell(opponentMovement);
        whoseTurn = roleContainer.getMyRole();
    }

    private boolean isMyTurn() {
        return whoseTurn == roleContainer.getMyRole();
    }

    public Role getWinner() {
        return gameField.getWinner();
    }

    public GameStatus getStatus() {
        return gameField.getStatus();
    }
}
