package com.pfcti.weather.entity;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WeatherHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private String historyID;

    private double latitude;

    private double longitude;

    private String weather;

    private double tempMin;

    private double tempMax;

    private double humidity;

    private Date created;



}
