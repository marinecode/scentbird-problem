package com.example.scentbirdproblem.role;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class RoleContainer {
    private Role myRole = Role.NONE;
    private Role opponentRole = Role.NONE;

    public synchronized void setMyRole(Role myRole) {
        if (!isRoleSet()) {
            this.myRole = myRole;
            this.opponentRole = Role.getOpposite(myRole);
        } else {
            log.info("Role is already set.");
        }
    }

    public Role getMyRole() {
        return myRole;
    }

    public Role getOpponentRole() {
        return opponentRole;
    }

    public boolean isRoleSet() {
        return !myRole.equals(Role.NONE);
    }

}
