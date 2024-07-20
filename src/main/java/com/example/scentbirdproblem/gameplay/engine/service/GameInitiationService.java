package com.example.scentbirdproblem.gameplay.engine.service;

import com.example.scentbirdproblem.gameplay.engine.GameEngine;
import com.example.scentbirdproblem.opponent.connector.OpponentConnector;
import com.example.scentbirdproblem.role.Role;
import com.example.scentbirdproblem.role.RoleContainer;
import com.example.scentbirdproblem.role.dto.response.RoleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log
@RequiredArgsConstructor
@ConditionalOnProperty(value = "role.selection.auto", havingValue = "true", matchIfMissing = true)
public class GameInitiationService {

    private final OpponentConnector opponentConnector;
    private final RoleContainer roleContainer;
    private final GameEngine gameEngine;

    @EventListener(ApplicationReadyEvent.class)
    public void initGame() {
        determineRoles();
        gameEngine.startGame();
    }

    private void determineRoles() {
        if (roleContainer.isRoleSet()) {
            log.info("Role is already set. My role is " + roleContainer.getMyRole());
            return;
        }

        log.info("Role is not set. Initiating role selection");

        RoleResponseDto opponentRole = opponentConnector.getOpponentRole();
        roleContainer.setMyRole(Role.getOpposite(opponentRole.role()));

        log.info("Role selection is done. My role is " + roleContainer.getMyRole());
    }
}


