package com.pfcti.weather.common;



/**
 * Global Constants specification.
 *
 * @author jponce on 2023/08/10.
 * @version 1.0
 */
public final class WeatherConstants {

    //Weather Api Constants
    public static final String URL_WEATHER_API = "https://api.openweathermap.org/data/2.5/weather";
    public static final String API_KEY = "f4010aa70aa1300ce86a401803d670eb";
    public static final String STATUS_CODE_ERROR = "503";
    public static final String STATUS_CODE_SUCCESSFULL = "200";


    private WeatherConstants() {
        super();
    }

}
