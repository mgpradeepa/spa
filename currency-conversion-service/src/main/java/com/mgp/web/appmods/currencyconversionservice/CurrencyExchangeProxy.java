package com.mgp.web.appmods.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8001")
// to make it work with Eureka and load balancing
// make below change by removing url
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
     public CurrencyConversion retrieveChangeVariables(@PathVariable String from, @PathVariable String to ) ;
}
