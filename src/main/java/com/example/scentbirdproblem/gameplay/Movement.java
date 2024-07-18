package com.example.scentbirdproblem.gameplay;

import com.example.scentbirdproblem.role.Role;


public class Movement {
    private final int id;
    private final int x;
    private final int y;
    private final Role role;
    private boolean isCommitted;

    public Movement(int id, int x, int y, Role role) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.role = role;
        this.isCommitted = false;
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
}
