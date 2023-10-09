package com.pfcti.weather.controller;



import com.pfcti.weather.request.WeatherRequest;
import com.pfcti.weather.response.CustomApiResponse;
import com.pfcti.weather.service.IWeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Weather api", description = "Manage Information of weather")
public class WeatherController {

    @Lazy
    @Autowired
    IWeatherService service;

    @Operation(summary = "Find and save weather")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success Response",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = CustomApiResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Service not found",
            content = @Content)})
    @PostMapping
    public ResponseEntity<CustomApiResponse> findAndSaveWeather(@Valid @RequestBody WeatherRequest request){
        CustomApiResponse response= service.findWeather(request);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Find weather list saved")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success Response",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = CustomApiResponse.class))}),
        @ApiResponse(responseCode = "404", description = "Service not found",
            content = @Content)})
    @GetMapping
    public ResponseEntity<CustomApiResponse> findSavedWeatherList(){
        CustomApiResponse response= service.findWeatherList();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }


}
