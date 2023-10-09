package com.pfcti.weather.request;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequest {

    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = Integer.MAX_VALUE)
    private Double lat;

    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = Integer.MAX_VALUE)
    private Double lon;

}
