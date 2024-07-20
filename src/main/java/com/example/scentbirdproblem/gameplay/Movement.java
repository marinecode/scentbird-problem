package com.example.scentbirdproblem.gameplay;

import com.example.scentbirdproblem.role.Role;


public class Movement {
    private static int id = 1;
    private final int x;
    private final int y;
    private final Role role;
    private boolean isCommitted;

    private Movement(int id, int x, int y, Role role) {
        Movement.id = id;
        this.x = x;
        this.y = y;
        this.role = role;
        this.isCommitted = false;
    }

    public static Movement create(int x, int y, Role role) {
        return new Movement(id++, x, y, role);
    }

    public void commit() {
        this.isCommitted = true;
    }

    public boolean isCommitted() {
        return this.isCommitted;
    }

    public int getId() {
        return this.id;
    }

    public Role getRole() {
        return this.role;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
