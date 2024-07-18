package com.example.scentbirdproblem.role.controller;

import com.example.scentbirdproblem.constant.URL;
import com.example.scentbirdproblem.role.response.RoleResponseDto;
import com.example.scentbirdproblem.role.service.RoleSelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController(URL.ROLE_ROOT)
public class RoleController {

    final private RoleSelectionService roleSelectionService;

    @GetMapping(URL.ROLE_OPPONENT)
    public RoleResponseDto getOpponentRole(@RequestParam(required = false, value = "suggestion") int opponentSuggestion) {
        return roleSelectionService.chooseOpponentRole(opponentSuggestion);
    }
}
