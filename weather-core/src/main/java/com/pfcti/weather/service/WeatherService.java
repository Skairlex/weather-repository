package com.pfcti.weather.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.pfcti.weather.common.WeatherConstants;
import com.pfcti.weather.entity.WeatherHistory;
import com.pfcti.weather.repository.IWheatherRepository;
import com.pfcti.weather.request.WeatherRequest;
import com.pfcti.weather.response.CustomApiResponse;
import com.pfcti.weather.response.WeatherHistoryResponse;
import com.pfcti.weather.utils.AutorizacionesMovilWebService;
import com.pfcti.weather.vo.weatherApi.ResponseApiWeather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Weather Service implementation
 *
 * @author jponce on 2023/08/10.
 * @version 1.0
 */

@Lazy
@Service
@Slf4j
public class WeatherService implements IWeatherService{

    @Autowired
    private IWheatherRepository weatherRepository;


    @Override
    public CustomApiResponse findWeather(WeatherRequest request) {
        CustomApiResponse<WeatherHistoryResponse> response= new CustomApiResponse<>();
        List<String> errors= new ArrayList<>();
        WeatherHistory weatherResponse= WeatherHistory.builder().build();
        try {
            List<WeatherHistory> foundedList = weatherRepository.findByLatitudeAndLongitudeOrderByCreatedDesc(
                request.getLat(), request.getLon());
            Boolean exist = false;
            Date actualTime = new Date();
            if(!foundedList.isEmpty()){
                //Get first of list because is the most recent
                WeatherHistory found =foundedList.get(0);
                long differenceInMilis = actualTime.getTime() - found.getCreated().getTime();
                long differenceInMinutes = differenceInMilis / (60 * 1000);
                if (differenceInMinutes < 10) {
                    exist = true;
                    weatherResponse = found;
                }
            }
            ResponseApiWeather weather = null;
            if (!exist) {
                weather = AutorizacionesMovilWebService.findDataWeather(request.getLat(),
                    request.getLon(), WeatherConstants.API_KEY);
                String weatherDescription=weather.getWeather().stream().map(desc->desc.getMain()).collect(
                    Collectors.joining(", "));
                weatherResponse=WeatherHistory.builder()
                    .latitude(weather.getCoordinates().getLatitude())
                    .longitude(weather.getCoordinates().getLongitude())
                    .weather(weatherDescription)
                    .tempMin(weather.getMainWeather().getTempMin())
                    .tempMax(weather.getMainWeather().getTempMax())
                    .humidity(weather.getMainWeather().getHumidity())
                    .created(actualTime)
                    .build();
                weatherRepository.save(weatherResponse);
            }
        }catch (Exception e){
            log.error("Error on saving weather:",e);
            response.setCode(WeatherConstants.STATUS_CODE_ERROR);
            errors.add("Error on saving weather");
            response.setErrors(errors);
            return response;
        }
        WeatherHistoryResponse responseCopy=WeatherHistoryResponse.builder()
            .weather(weatherResponse.getWeather())
            .tempMin(weatherResponse.getTempMin())
            .tempMax(weatherResponse.getTempMax())
            .humidity(weatherResponse.getHumidity())
            .build();
        response.setResponse(responseCopy);
        response.setCode(WeatherConstants.STATUS_CODE_SUCCESSFULL);
        return response;
    }

    @Override
    public CustomApiResponse findWeatherList() {
        List<WeatherHistory> foundedList = weatherRepository.findAll();
        CustomApiResponse<List<WeatherHistory>> response= new CustomApiResponse<>();
        response.setResponse(foundedList);
        response.setCode(WeatherConstants.STATUS_CODE_SUCCESSFULL);
        return response;
    }
}
