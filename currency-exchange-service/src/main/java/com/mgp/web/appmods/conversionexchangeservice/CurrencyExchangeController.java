package com.mgp.web.appmods.conversionexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;
    @Autowired
    Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveChangeVariables(@PathVariable String from, @PathVariable String to ) {
        // this is to get from in memory
//        var currencyExchange = new CurrencyExchange(100L, from, to, BigDecimal.valueOf(50));

        //fetching from JPa h2 table
        var currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to );
        if(currencyExchange == null) {
            throw new RuntimeException("CurrencyExchange for this combo is not avaialble");
        }

        var port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;


    }
}
