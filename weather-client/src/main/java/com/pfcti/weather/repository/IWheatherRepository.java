package com.pfcti.weather.repository;

import java.util.List;
import com.pfcti.weather.entity.WeatherHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWheatherRepository extends JpaRepository<WeatherHistory,String> {

    List<WeatherHistory> findByLatitudeAndLongitudeOrderByCreatedDesc(double latitude, double longitude);


}
