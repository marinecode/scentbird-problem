package com.example.scentbirdproblem.gameplay.movement.service;

import com.example.scentbirdproblem.gameplay.movement.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Primary
@ConditionalOnProperty(value = "movement.delay.enabled")
public class DelayedMyMovementService implements MyMovementService {

    private final MyMovementServiceImpl myMovementService;
    private final ScheduledExecutorService delayedExecutor = Executors.newSingleThreadScheduledExecutor();

    @Value("${movement.delay.seconds}")
    private long delay;

    @Override
    public Movement suggestMovement(String[][] gameField) {
        ScheduledFuture<Movement> suggestedMovementFuture = delayedExecutor.schedule(() -> myMovementService.suggestMovement(gameField), delay, TimeUnit.SECONDS);
        Movement suggestedMovement;
        try {
            suggestedMovement = suggestedMovementFuture.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return suggestedMovement;
    }

    @Override
    public Movement commitMovement() {
        ScheduledFuture<Movement> committedMovementFuture =
                delayedExecutor.schedule(myMovementService::commitMovement, delay, java.util.concurrent.TimeUnit.SECONDS);
        Movement committedMovement;
        try {
            committedMovement = committedMovementFuture.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return committedMovement;
    }
}
