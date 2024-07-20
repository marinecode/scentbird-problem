package com.example.scentbirdproblem.gameplay.movement;

import java.util.Stack;

public class MovementStorage {
    private final Stack<Movement> movementLog = new Stack<>();

    public void addMovement(Movement movement) {
        movementLog.push(movement);
    }

    public void commit() {
        movementLog.peek().commit();
    }

    public Movement getLastMovement() {
        return movementLog.peek();
    }
}
