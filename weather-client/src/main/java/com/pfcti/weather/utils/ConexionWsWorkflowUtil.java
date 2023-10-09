/**
 * 
 */
package com.pfcti.weather.utils;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * ConexionWsWorkflowUtil.
 * 
 * @author jponce
 */

@Slf4j
public final class ConexionWsWorkflowUtil {

    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * Class constructor.
     */
    private ConexionWsWorkflowUtil() {
        super();
    }


    /**
     *
     * @param url
     * @param object
     * @param validateWorkItem
     * @return
     * @throws IOException
     */
    public static String executePostRestTemplate(String url, Object object, Boolean validateWorkItem) throws IOException {

        String result = "";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.MULTIPART_FORM_DATA_VALUE);
        headers.add("Accept", "application/json");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("data", object);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
        result = restTemplate.postForObject(url, requestEntity, String.class);

        log.info("Respuesta = {}", result);
        return result;
    }


    /**
     *
     * @param url
     * @param object
     * @return
     * @throws IOException
     */
    public static String executeGetRestTemplate(String url, Object object) throws IOException {

        String result = "";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.MULTIPART_FORM_DATA_VALUE);
        headers.add("Accept", "application/json");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("data", object);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
        ResponseEntity<String> resultResponse = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        if(resultResponse.getStatusCode().value()==200){
            result=resultResponse.getBody();
        }
        else {
            log.error("Error getting response of:"+url+" , status: "+resultResponse.getStatusCode().value());
        }
        log.info("Respuesta = {}", result);
        return result;
    }


}
