package com.example.scentbirdproblem.gameplay;

import com.example.scentbirdproblem.gameplay.exception.IllegalMovementException;
import com.example.scentbirdproblem.role.Role;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.example.scentbirdproblem.constant.GamePlayConstants.FIELD_SIZE;

public class GameField {
    private Role winner = Role.NONE;
    private GameStatus status = GameStatus.NOT_STARTED;
    private final String[][] field = new String[FIELD_SIZE][FIELD_SIZE]; //TODO change String to Cell objects
    private final Map<Role, Map<FinishType, Integer>> finishCounter = new HashMap<>();

    public GameField() {
        finishCounter.put(Role.X, new HashMap<>() {{
            put(FinishType.DRAW, 0);
            put(FinishType.DIAGONAL_WIN, 0);
            put(FinishType.HORIZONTAL_WIN, 0);
            put(FinishType.VERTICAL_WIN, 0);
        }});
        finishCounter.put(Role.O, new HashMap<>() {{
            put(FinishType.DRAW, 0);
            put(FinishType.DIAGONAL_WIN, 0);
            put(FinishType.HORIZONTAL_WIN, 0);
            put(FinishType.VERTICAL_WIN, 0);
        }});
    }

    public void occupyCell(Movement movement) {
        //TODO validate if Role not NONE and that movement is not committed and movement is inside the field and the game is not finished
        if (field[movement.getX()][movement.getY()] != null) {
            throw new IllegalMovementException("Cell " + movement + " is already occupied");
        }
        field[movement.getX()][movement.getY()] = movement.getRole().name();
        updateFinishCounter(movement);
        updateStatus();
    }

    public void deOccupyCell(Movement movement) {
        field[movement.getX()][movement.getY()] = null;
    }

    public String[][] getField() {
        String[][] copy = new String[field.length][];
        for (int i = 0; i < field.length; i++) {
            copy[i] = Arrays.copyOf(field[i], field[i].length);
        }
        return copy;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Role getWinner() {
        return winner;
    }

    private void updateFinishCounter(Movement movement) {
        Role role = movement.getRole();
        Map<FinishType, Integer> roleFinishCounter = finishCounter.computeIfAbsent(role, finishType -> new HashMap<>());

        int x = movement.getX();
        int y = movement.getY();

        roleFinishCounter.merge(FinishType.DRAW, 1, Integer::sum);
        if (x == y) {
            roleFinishCounter.merge(FinishType.DIAGONAL_WIN, 1, Integer::sum);
        }
        if (x == 0) {
            roleFinishCounter.merge(FinishType.VERTICAL_WIN, 1, Integer::sum);
        }
        if (y == 0) {
            roleFinishCounter.merge(FinishType.HORIZONTAL_WIN, 1, Integer::sum);
        }
    }

    private void updateStatus() {
        int drawCount = 0;
        for (Map.Entry<Role, Map<FinishType, Integer>> entry : finishCounter.entrySet()) {
            drawCount += entry.getValue().get(FinishType.DRAW);
            Role role = entry.getKey();
            Map<FinishType, Integer> roleFinishCounter = entry.getValue();

            if (doesHaveWinner(roleFinishCounter)) {
                winner = role;
                status = GameStatus.FINISHED;
                return;
            } else {
                status = GameStatus.IN_PROGRESS;
            }
        }
        if (isDraw(drawCount)) {
            status = GameStatus.FINISHED;
        }
    }

    private boolean doesHaveWinner(Map<FinishType, Integer> roleFinishCounter) {
        return roleFinishCounter.get(FinishType.DIAGONAL_WIN) == FIELD_SIZE ||
                roleFinishCounter.get(FinishType.HORIZONTAL_WIN) == FIELD_SIZE ||
                roleFinishCounter.get(FinishType.VERTICAL_WIN) == FIELD_SIZE;
    }

    private boolean isDraw(int drawCount) {
        return status == GameStatus.IN_PROGRESS && drawCount == FIELD_SIZE * FIELD_SIZE;
    }
}
