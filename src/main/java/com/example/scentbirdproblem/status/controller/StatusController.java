package com.example.scentbirdproblem.status.controller;

import com.example.scentbirdproblem.constant.URL;
import com.example.scentbirdproblem.status.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URL.STATUS_ROOT)
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String getStatus() {
        return statusService.getStatus().toString();
    }
}
