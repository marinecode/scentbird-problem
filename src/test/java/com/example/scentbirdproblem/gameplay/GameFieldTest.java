package com.example.scentbirdproblem.gameplay;

import com.example.scentbirdproblem.gameplay.exception.IllegalMovementException;
import com.example.scentbirdproblem.gameplay.movement.Movement;
import com.example.scentbirdproblem.role.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GameFieldTest {

    @Test
    public void given_empty_field_when_check_status_then_status_not_started() {
        GameField gameField = new GameField();

        assertEquals(GameStatus.NOT_STARTED, gameField.getStatus());
    }

    @Test
    public void given_field_with_occupied_cell_when_occupy_cell_then_exception() {
        GameField gameField = new GameField();
        gameField.occupyCell(Movement.create(0, 0, Role.X));

        Exception ex = assertThrows(IllegalMovementException.class,
                () -> gameField.occupyCell(Movement.create(0, 0, Role.X)));

        assertEquals("Cell [0,0] is already occupied", ex.getMessage());

    }

    @Test
    public void given_field_with_one_movement_when_check_status_then_status_in_progress() {
        GameField gameField = new GameField();
        gameField.occupyCell(Movement.create(0, 0, Role.X));

        assertEquals(GameStatus.IN_PROGRESS, gameField.getStatus());
    }

    @Test
    public void given_field_with_vertical_winner_when_check_status_then_status_finished() {
        GameField gameField = new GameField();
        gameField.occupyCell(Movement.create(0, 0, Role.X));
        gameField.occupyCell(Movement.create(0, 1, Role.X));
        gameField.occupyCell(Movement.create(0, 2, Role.X));

        assertEquals(GameStatus.FINISHED, gameField.getStatus());
        assertEquals(Role.X, gameField.getWinner());
    }

    @Test
    public void given_field_with_horizontal_winner_when_check_status_then_status_finished() {
        GameField gameField = new GameField();
        gameField.occupyCell(Movement.create(0, 0, Role.O));
        gameField.occupyCell(Movement.create(1, 0, Role.O));
        gameField.occupyCell(Movement.create(2, 0, Role.O));

        assertEquals(GameStatus.FINISHED, gameField.getStatus());
        assertEquals(Role.O, gameField.getWinner());
    }

    @Test
    public void given_field_with_diagonal_winner_when_check_status_then_status_finished() {
        GameField gameField = new GameField();
        gameField.occupyCell(Movement.create(0, 0, Role.X));
        gameField.occupyCell(Movement.create(1, 1, Role.X));
        gameField.occupyCell(Movement.create(2, 2, Role.X));

        assertEquals(GameStatus.FINISHED, gameField.getStatus());
        assertEquals(Role.X, gameField.getWinner());
    }

    @Test
    public void given_field_with_draw_when_check_status_then_status_draw() {
        GameField gameField = new GameField();
        gameField.occupyCell(Movement.create(0, 0, Role.X));
        gameField.occupyCell(Movement.create(0, 1, Role.O));
        gameField.occupyCell(Movement.create(0, 2, Role.X));
        gameField.occupyCell(Movement.create(1, 0, Role.O));
        gameField.occupyCell(Movement.create(1, 1, Role.X));
        gameField.occupyCell(Movement.create(1, 2, Role.O));
        gameField.occupyCell(Movement.create(2, 0, Role.O));
        gameField.occupyCell(Movement.create(2, 1, Role.X));
        gameField.occupyCell(Movement.create(2, 2, Role.O));
        System.out.println(GameFieldVisualizer.getView(gameField));

        assertEquals(GameStatus.FINISHED, gameField.getStatus());
        assertEquals(Role.NONE, gameField.getWinner());
    }

}
