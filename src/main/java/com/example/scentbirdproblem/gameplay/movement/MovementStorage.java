package com.example.scentbirdproblem.gameplay.movement;

import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class MovementStorage {
    private final Stack<Movement> movementLog = new Stack<>();

    public synchronized void addMovement(Movement movement) {
        if (!movementLog.isEmpty()) {
            Movement lastMovement = getLastMovement();
            if (!movementLog.isEmpty() && movement.getId().equals(lastMovement.getId())) {
                return;
            }
            if (!lastMovement.isCommitted()) {
                throw new IllegalStateException("Can't add movement (" + movement.getId() + "). Last movement is not committed");
            }
        }
        movementLog.push(movement);
    }

    public synchronized void commit() {
        movementLog.peek().commit();
    }

    public boolean isEmpty() {
        return movementLog.isEmpty();
    }

    public synchronized Movement getLastMovement() {
        return movementLog.peek();
    }
}
