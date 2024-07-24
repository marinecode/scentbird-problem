package com.example.scentbirdproblem.gameplay.movement;

import com.example.scentbirdproblem.gameplay.dto.request.MovementPrepareRequestDto;
import com.example.scentbirdproblem.role.Role;

import java.util.UUID;


public class Movement {
    private UUID id;
    private final int x;
    private final int y;
    private final Role role;
    private boolean isCommitted;

    private Movement(int x, int y, Role role) {
        this.id = UUID.randomUUID();
        this.x = x;
        this.y = y;
        this.role = role;
        this.isCommitted = false;
    }

    private Movement(UUID id, int x, int y, Role role) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.role = role;
        this.isCommitted = false;
    }

    public static Movement create(int x, int y, Role role) {
        return new Movement(x, y, role);
    }

    public static Movement create(MovementPrepareRequestDto requestDto) {
        return new Movement(requestDto.getId(), requestDto.getX(), requestDto.getY(), requestDto.getRole());
    }

    public void commit() {
        this.isCommitted = true;
    }

    public boolean isCommitted() {
        return this.isCommitted;
    }

    public UUID getId() {
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
        return role + " [" + x + "," + y + "]";
    }
}
