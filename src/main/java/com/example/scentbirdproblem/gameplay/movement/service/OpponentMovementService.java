package com.example.scentbirdproblem.gameplay.movement.service;

import com.example.scentbirdproblem.gameplay.engine.GameEngine;
import com.example.scentbirdproblem.gameplay.exception.NothingToCommitException;
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
        log.info("Opponent movement " + opponentMovement + " is valid");
        movementStorage.addMovement(opponentMovement);
    }

    public void commitMovement(UUID opponentMovementId) {
        if (movementStorage.isEmpty()) {
            throw new NothingToCommitException();
        }
        Movement lastMovement = movementStorage.getLastMovement();
        if (!lastMovement.getId().equals(opponentMovementId)) {
            throw new IllegalArgumentException("Movement id doesn't match the last prepared movement");
        } else if (lastMovement.isCommitted()) {
            log.info("Movement (" + lastMovement.getId() + ") already committed");
        } else {
            movementStorage.commit();
            log.info("Opponent movement " + lastMovement + " committed");
            gameEngine.occupyCellInOpponentTurn(lastMovement);
        }
    }
}
