package com.pfcti.weather.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherVo {

    private String latitude;
    private String longitude;

}
