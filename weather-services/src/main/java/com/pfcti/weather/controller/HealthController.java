package com.pfcti.weather.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/status")
@Lazy
public class HealthController {

    @GetMapping
    public HttpStatus success() {
        return HttpStatus.OK;
    }

}
