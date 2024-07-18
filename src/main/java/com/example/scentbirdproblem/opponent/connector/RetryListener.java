package com.example.scentbirdproblem.opponent.connector;

import org.springframework.retry.RetryContext;
import org.springframework.retry.interceptor.MethodInvocationRetryCallback;
import org.springframework.retry.listener.MethodInvocationRetryListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class RetryListener extends MethodInvocationRetryListenerSupport {


    @Override
    protected <T, E extends Throwable> void doOnError(RetryContext context, MethodInvocationRetryCallback<T, E> callback, Throwable throwable) {
        System.out.println("Retry no. " + context.getRetryCount() + ". Exception: " + context.getLastThrowable().getMessage());
    }
}
