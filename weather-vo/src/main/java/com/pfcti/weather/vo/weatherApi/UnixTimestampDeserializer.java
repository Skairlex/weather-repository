package com.pfcti.weather.vo.weatherApi;

import java.io.IOException;
import java.util.Date;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnixTimestampDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        long unixTimestamp = 0; // Obtener el valor del campo como long
        try {
            unixTimestamp = jsonParser.getValueAsLong();
        } catch (IOException e) {
            log.info("Error on deserialize timestamp : ",e);
        }

        // Convertir el Unix Timestamp a una fecha y hora legible
        Date fecha = new Date(unixTimestamp * 1000); // Multiplicar por 1000 para convertir a milisegundos

        return fecha;
    }
}
