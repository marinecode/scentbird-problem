package com.example.scentbirdproblem.role;

public enum Role {
    X,
    O,
    NONE;

    public static Role getOpposite(Role role) {
        if (role == X) {
            return O;
        }
        return X;
    }
}
