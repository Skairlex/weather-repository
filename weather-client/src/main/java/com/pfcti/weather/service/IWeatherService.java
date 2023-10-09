package com.pfcti.weather.service;


import com.pfcti.weather.request.WeatherRequest;
import com.pfcti.weather.response.CustomApiResponse;

/**
 * Weather service specification
 *
 * @author jponce
 * @since 2023/10/08
 * @version 0.0.1
 *
 */

public interface IWeatherService {

     /**
      * Find actual weather
      * @return
      */
     CustomApiResponse findWeather(WeatherRequest request);

     /**
      * Find List weather of repository
      * @return
      */
     CustomApiResponse findWeatherList();


}
