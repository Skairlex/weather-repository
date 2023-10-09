package com.pfcti.weather.controller;

import com.pfcti.weather.response.CustomApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/status")
@Lazy
@Tag(name = "Status", description = "review Status System")
public class HealthController {

    @Operation(summary = "Status system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success Response",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = HttpStatus.class))}),
        @ApiResponse(responseCode = "404", description = "Service not found",
            content = @Content)})
    @GetMapping
    public HttpStatus success() {
        return HttpStatus.OK;
    }

}
