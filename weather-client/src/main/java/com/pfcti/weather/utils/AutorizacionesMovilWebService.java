/**
 * 
 */
package com.pfcti.weather.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfcti.weather.common.WeatherConstants;
import com.pfcti.weather.vo.weatherApi.ResponseApiWeather;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * AutorizacionesMovilWebService.
 * 
 * @author hsanchez
 */

@Slf4j
public final class AutorizacionesMovilWebService {


    /**
     * Variable defined for message error.
     */
    public static final String MENSAJE_ERROR = "Error en el proceso ";
    private static ObjectMapper objectMapper = new ObjectMapper();;
    /**
     * Class Constructor.
     */
    private AutorizacionesMovilWebService() {
        super();
    }


    /**
     *  Method to Find flows weather Information.
     * @param latitude
     * @param longitude
     * @param key
     * @return
     */
    public static ResponseApiWeather findDataWeather(double latitude, double longitude,String key) {
        String jsonResponse = null;
        try {
            String url = WeatherConstants.URL_WEATHER_API;
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("appid", key);

            jsonResponse = ConexionWsWorkflowUtil.executeGetRestTemplate(uriBuilder.toUriString(),null);

            if (StringUtils.isNotEmpty(jsonResponse)) {
                return objectMapper.readValue(jsonResponse, ResponseApiWeather.class);

            }

        } catch (IOException e) {
            log.info(MENSAJE_ERROR, e);
        }

        return null;

    }


}
