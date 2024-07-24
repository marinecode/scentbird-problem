package com.example.scentbirdproblem.gameplay.movement.service;

import com.example.scentbirdproblem.gameplay.movement.Movement;
import org.springframework.stereotype.Service;

@Service

public interface MyMovementService {

    void suggestMovement(String[][] gameField);

    Movement commitMovement();
}
