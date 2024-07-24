package com.example.scentbirdproblem.gameplay.movement.service;

import com.example.scentbirdproblem.gameplay.movement.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
@RequiredArgsConstructor
public class DelayedMyMovementService implements MyMovementService {

    private final MyMovementServiceImpl myMovementService;
    private ScheduledExecutorService delayedExecutor = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void suggestMovement(String[][] gameField) {
//        delayedExecutor.schedule()
        myMovementService.suggestMovement(gameField);
    }

    @Override
    public Movement commitMovement() {
        return myMovementService.commitMovement();
    }
}
