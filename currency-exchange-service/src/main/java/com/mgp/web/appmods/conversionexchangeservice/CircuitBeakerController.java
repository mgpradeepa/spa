package com.mgp.web.appmods.conversionexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBeakerController {
    private final Logger LOGGER = LoggerFactory.getLogger(CircuitBeakerController.class);
    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {

        LOGGER.info("Sample API hit and run");
        // make it fail to reach the API

        ResponseEntity<String> forentity = new RestTemplate().getForEntity("http://localhost:8080/chumma-url", String.class);
//        return "sample API";
        return forentity.getBody();

    }

    public String hardcodedResponse(){
        return "fallback-Response";
    }
}
