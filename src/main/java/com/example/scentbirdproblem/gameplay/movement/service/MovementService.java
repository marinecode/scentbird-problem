package com.example.scentbirdproblem.gameplay.movement.service;

import com.example.scentbirdproblem.gameplay.movement.Movement;
import com.example.scentbirdproblem.gameplay.movement.MovementStorage;
import com.example.scentbirdproblem.opponent.connector.OpponentConnector;
import com.example.scentbirdproblem.role.Role;
import com.example.scentbirdproblem.role.RoleContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MovementService {

    private final RoleContainer roleContainer;
    private final OpponentConnector opponentConnector;

    private MovementStorage movementStorage = new MovementStorage();
    private Role whoseTurn;

    public void prepareMovement(String[][] gameField) {
        Movement suggestedMovement = suggestMove(gameField);
        opponentConnector.prepareMovement(suggestedMovement);
        movementStorage.addMovement(suggestedMovement);
    }

    public Movement commitMovement() {
        Movement movementToCommit = movementStorage.getLastMovement();
        opponentConnector.commitMovement(movementToCommit);
        movementStorage.commit();
        return movementToCommit;
    }

    public Role whoseTurn() {
        return whoseTurn;
    }

    private Movement suggestMove(String[][] gameField) {
        Random random = new Random();
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        String cell = gameField[x][y];
        while (!cell.equals(" ")) {
            x = random.nextInt(3);
            y = random.nextInt(3);
            cell = gameField[x][y];
        }
        return Movement.create(x, y, roleContainer.getMyRole()); //TODO don't know what to do with the ID
    }
}
