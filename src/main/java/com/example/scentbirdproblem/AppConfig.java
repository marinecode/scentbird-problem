package com.example.scentbirdproblem;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableRetry
@EnableScheduling
public class AppConfig {
}
