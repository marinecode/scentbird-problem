package com.example.scentbirdproblem.role;

import com.example.scentbirdproblem.role.exception.RoleCantBeChangedException;
import org.springframework.stereotype.Component;

@Component
public class RoleContainer {
    private Role myRole = Role.NONE;
    private Role opponentRole = Role.NONE;

    public void setMyRole(Role myRole) {
        if (this.myRole == Role.NONE) {
            this.myRole = myRole;
            this.opponentRole = Role.getOpposite(myRole);
        }
        throw new RoleCantBeChangedException();
    }

    public String getMyRole() {
        return myRole.name();
    }

    public String getOpponentRole() {
        return opponentRole.name();
    }

    public boolean isRoleSet() {
        return !myRole.equals(Role.NONE);
    }

}
