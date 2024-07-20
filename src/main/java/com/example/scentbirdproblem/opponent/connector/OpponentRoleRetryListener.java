package com.example.scentbirdproblem.opponent.connector;

import lombok.extern.java.Log;
import org.springframework.retry.RetryContext;
import org.springframework.retry.interceptor.MethodInvocationRetryCallback;
import org.springframework.retry.listener.MethodInvocationRetryListenerSupport;
import org.springframework.stereotype.Component;

@Log
@Component
public class OpponentRoleRetryListener extends MethodInvocationRetryListenerSupport {


    @Override
    protected <T, E extends Throwable> void doOnError(RetryContext context, MethodInvocationRetryCallback<T, E> callback, Throwable throwable) {
        log.info("Retry no. " + context.getRetryCount() + ". Exception: " + context.getLastThrowable().getMessage());
    }
}
