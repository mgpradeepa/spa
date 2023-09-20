package com.mgp.web.appmods.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class CurrencyConversionController {

@Autowired
 CurrencyExchangeProxy currencyExchangeProxy;
@Autowired
Environment environment;


    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}/")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to , @PathVariable BigDecimal quantity){


        // for variables to  pass as hashmap

        var uriVars = new HashMap<String, String >();
        uriVars.put("from", from);
        uriVars.put("to", to);
       var responseEntity = new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVars);
       var currencyConversion = responseEntity.getBody();
//        var port = environment.getProperty("local.server.port");
//        currencyConversion.setEnvironment(port);
        return new CurrencyConversion(currencyConversion.getId()
                , from, to , quantity,
                currencyConversion.getConversionValue(), quantity.multiply(currencyConversion.getConversionValue()), currencyConversion.getEnvironment());

    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}/")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to , @PathVariable BigDecimal quantity){

        // for variables to  pass as hashmap

        var currencyConversion = currencyExchangeProxy.retrieveChangeVariables(from, to);
//        currencyConversion.setEnvironment(Objects.requireNonNull(environment.getProperty("local.server.port")));
        return new CurrencyConversion(currencyConversion.getId()
                , from, to , quantity,
                currencyConversion.getConversionValue(),
                quantity.multiply(currencyConversion.getConversionValue()),
                currencyConversion.getEnvironment() + " feign" );

    }
}
