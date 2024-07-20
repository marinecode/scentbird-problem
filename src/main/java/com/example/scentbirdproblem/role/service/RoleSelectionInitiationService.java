package com.example.scentbirdproblem.role.service;

import com.example.scentbirdproblem.opponent.connector.OpponentConnector;
import com.example.scentbirdproblem.role.Role;
import com.example.scentbirdproblem.role.RoleContainer;
import com.example.scentbirdproblem.role.dto.response.RoleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "role.selection.auto", havingValue = "true", matchIfMissing = true)
public class RoleSelectionInitiationService {

    private final OpponentConnector opponentConnector;
    private final RoleContainer roleContainer;

    @EventListener(ApplicationReadyEvent.class)
    public void initRoleSelection() {
        if (roleContainer.isRoleSet()) {
            System.out.println("Role is already set. My role is " + roleContainer.getMyRole());
            return;
        }

        System.out.println("Role is not set. Initiating role selection");
        try {
            RoleResponseDto opponentRole = opponentConnector.getOpponentRole();
            roleContainer.setMyRole(Role.getOpposite(opponentRole.role()));
        } catch (Exception e) {
            System.out.println("Failed to get opponent role. Try one more time");
        }
        System.out.println("Role selection is done. My role is " + roleContainer.getMyRole());
    }
}


