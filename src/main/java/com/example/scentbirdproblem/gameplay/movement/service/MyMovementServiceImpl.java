package com.example.scentbirdproblem.gameplay.movement.service;

import com.example.scentbirdproblem.gameplay.movement.Movement;
import com.example.scentbirdproblem.gameplay.movement.MovementStorage;
import com.example.scentbirdproblem.opponent.connector.OpponentConnector;
import com.example.scentbirdproblem.role.RoleContainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Log
@RequiredArgsConstructor
public class MyMovementServiceImpl implements MyMovementService {

    private final RoleContainer roleContainer;
    private final OpponentConnector opponentConnector;
    private final MovementStorage movementStorage;

    public void suggestMovement(String[][] gameField) {
        Movement suggestedMovement = suggestMove(gameField);
        //TODO handle validation error (suggest another move)
        opponentConnector.prepareMovement(suggestedMovement);
        log.info("Prepared movement: " + suggestedMovement);
        movementStorage.addMovement(suggestedMovement);
    }

    public Movement commitMovement() {
        //Todo Handle abort event
        Movement movementToCommit = movementStorage.getLastMovement();
        opponentConnector.commitMovement(movementToCommit);
        movementStorage.commit();
        return movementToCommit;
    }


    private Movement suggestMove(String[][] gameField) {
        Random random = new Random();
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        String cell = gameField[x][y];
        while (cell != null) {
            x = random.nextInt(3);
            y = random.nextInt(3);
            cell = gameField[x][y];
        }
        return Movement.create(x, y, roleContainer.getMyRole());
    }
}
