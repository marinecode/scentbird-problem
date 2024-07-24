package com.example.scentbirdproblem.gameplay.movement.service;

import com.example.scentbirdproblem.gameplay.engine.GameEngine;
import com.example.scentbirdproblem.gameplay.movement.Movement;
import com.example.scentbirdproblem.gameplay.movement.MovementStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log
@RequiredArgsConstructor
public class OpponentMovementService {

    private final GameEngine gameEngine;
    private final MovementStorage movementStorage;

    public void prepareMovement(Movement opponentMovement) {
        gameEngine.checkIfMovementValid(opponentMovement);
        movementStorage.addMovement(opponentMovement);
    }

    public void commitMovement(UUID opponentMovementId) {
        Movement lastMovement = movementStorage.getLastMovement();
        if (!lastMovement.getId().equals(opponentMovementId)) {
            throw new IllegalArgumentException("Movement id doesn't match the last movement");
        } else if (lastMovement.isCommitted()) {
            log.info("Movement (" + lastMovement.getId() + ") already committed");
        } else {
            movementStorage.commit();
            gameEngine.occupyCellInOpponentTurn(lastMovement);
        }
    }
}
