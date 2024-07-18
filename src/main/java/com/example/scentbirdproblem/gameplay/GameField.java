package com.example.scentbirdproblem.gameplay;

public class GameField {
    private final String[][] field = new String[3][3];

    public void occupyCell(Movement movement) {
        if (field[movement.getX()][movement.getY()] != null) {
            throw new IllegalArgumentException("Cell is already occupied");
        }
        field[movement.getX()][movement.getY()] = movement.getRole().name();
    }

    public void deOccupyCell(Movement movement) {
        field[movement.getX()][movement.getY()] = null;
    }

    public String getRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(field[j][i] == null ? " " : field[j][i]);
                if (j != 2) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i != 2) {
                sb.append("-----\n");
            }
        }
        return sb.toString();
    }
}
