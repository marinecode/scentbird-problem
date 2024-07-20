package com.example.scentbirdproblem.gameplay;

import static com.example.scentbirdproblem.constant.GamePlayConstants.FIELD_SIZE;

public class GameFieldVisualizer {
    static final String EMPTY_CELL = " ";
    public static final String VERTICAL_DELIMITER = "|";
    public static final String HORIZONTAL_DELIMITER = "-----";
    public static final String NEW_LINE = "\n";

    public static String getView(GameField gameField) {

        StringBuilder sb = new StringBuilder();
        sb.append(NEW_LINE);
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                sb.append(gameField.field[x][y] == null ? EMPTY_CELL : gameField.field[x][y]);
                if (x != 2) {
                    sb.append(VERTICAL_DELIMITER);
                }
            }
            sb.append(NEW_LINE);
            if (y != 2) {
                sb.append(HORIZONTAL_DELIMITER);
                sb.append(NEW_LINE);
            }
        }
        return sb.toString();
    }

}
