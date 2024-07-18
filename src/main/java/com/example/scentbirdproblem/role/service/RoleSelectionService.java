package com.example.scentbirdproblem.role.service;

import com.example.scentbirdproblem.role.Role;
import com.example.scentbirdproblem.role.RoleContainer;
import com.example.scentbirdproblem.role.response.RoleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class RoleSelectionService {

private final RoleContainer roleContainer;
    private final Integer mySuggestion = new Random().nextInt();

    public RoleResponseDto chooseOpponentRole(int opponentSuggestion) {
        if (roleContainer.isRoleSet()) {
            return new RoleResponseDto(roleContainer.getMyRole());
        }

        Role chosenOpponentRole = chooseRole(opponentSuggestion);
        roleContainer.setMyRole(chosenOpponentRole);
        return new RoleResponseDto(chosenOpponentRole.name());
    }

    private Role chooseRole(int opponentSuggestion) {
        Role chosenOpponentRole = Role.X;
        if (opponentSuggestion > mySuggestion) {
            chosenOpponentRole = Role.O;
        }
        return chosenOpponentRole;
    }

    public int getMySuggestion() {
        return mySuggestion;
    }
}
