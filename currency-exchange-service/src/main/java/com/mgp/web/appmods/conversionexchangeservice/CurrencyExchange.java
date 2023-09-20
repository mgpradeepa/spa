package com.mgp.web.appmods.conversionexchangeservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyExchange {
// if we add jakarta entity it is ready to connect to the underlying db and become a table
    @NonNull
    @Id
    private Long id;
    @NonNull
    @Column(name = "currency_from")
    private String from;
    @NonNull
    @Column(name = "currency_to")
    private String to;
    @NonNull    private BigDecimal conversionValue;
    private String environment;


}
