package com.pfcti.weather.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherHistoryResponse {


    private String weather;

    private double tempMin;

    private double tempMax;

    private double humidity;

}
