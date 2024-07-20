package com.example.scentbirdproblem.gameplay.movement.controller;

import com.example.scentbirdproblem.constant.URL;
import com.example.scentbirdproblem.gameplay.dto.request.MovementCommitRequestDto;
import com.example.scentbirdproblem.gameplay.dto.request.MovementPrepareRequestDto;
import com.example.scentbirdproblem.gameplay.dto.response.MovementCommitResponseDto;
import com.example.scentbirdproblem.gameplay.dto.response.MovementPrepareResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URL.MOVEMENT_ROOT)
public class MovementController {

    @PostMapping(URL.MOVEMENT_PREPARE)
    public MovementPrepareResponseDto prepareMovement(@RequestBody MovementPrepareRequestDto requestDto) {
        return new MovementPrepareResponseDto(true);
    }

    @PostMapping(URL.MOVEMENT_COMMIT)
    public MovementCommitResponseDto commitMovement(@RequestBody MovementCommitRequestDto requestDto) {
        return new MovementCommitResponseDto();
    }
}
