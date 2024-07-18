package com.example.scentbirdproblem.role.controller;

import com.example.scentbirdproblem.constant.URL;
import com.example.scentbirdproblem.role.response.RoleResponseDto;
import com.example.scentbirdproblem.role.service.RoleSelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(URL.ROLE_ROOT)
@RestController
public class RoleController {

    final private RoleSelectionService roleSelectionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RoleResponseDto getOpponentRole(@RequestParam(required = false, value = "suggestion") Integer opponentSuggestion) {
        return roleSelectionService.chooseOpponentRole(opponentSuggestion);
    }
}
