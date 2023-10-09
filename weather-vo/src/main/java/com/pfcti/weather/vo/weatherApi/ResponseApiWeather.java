package com.pfcti.weather.vo.weatherApi;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ResponseApiWeather {

    @JsonProperty("coord")
    private Coordinates coordinates;

    @JsonProperty("main")
    private MainWeather mainWeather;

    @JsonProperty("weather")
    private List<Weather> weather;

    @JsonProperty("dt")
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    private Date dateCalculation;


}
