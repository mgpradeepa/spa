package com.mgp.web.appmods.currencyconversionservice;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
//@Entity
public class CurrencyConversion {

    @NonNull
    private long id;
    @NonNull
    private String from;
    @NonNull
    private String to;
    @NonNull
    private BigDecimal quantity;
    @NonNull
    private BigDecimal conversionValue;
    @NonNull
    private BigDecimal calcualtedAmount;
    @NonNull
    private String environment;

//    public CurrencyConversion(int i, String from, String to, BigDecimal quantity, BigDecimal one, BigDecimal ten, BigDecimal bigDecimal) {
//    }
}
