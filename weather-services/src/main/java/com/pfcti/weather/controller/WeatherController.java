package com.pfcti.weather.controller;



import com.pfcti.weather.request.WeatherRequest;
import com.pfcti.weather.response.CustomApiResponse;
import com.pfcti.weather.service.IWeatherService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
@Lazy
@Validated
public class WeatherController {

    @Lazy
    @Autowired
    IWeatherService service;


    @PostMapping
    public ResponseEntity<CustomApiResponse> findAndSaveWeather(@Valid @RequestBody WeatherRequest request){
        CustomApiResponse response= service.findWeather(request);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<CustomApiResponse> findSavedWeatherList(){
        CustomApiResponse response= service.findWeatherList();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }


}
