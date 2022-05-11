package com.mgp.web.appmods.oauth.securityconfig;

import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfiguration {

    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        http.authorizeExchange()
                .pathMatchers("/actuator/**")
                .permitAll()

                .and()

                .authorizeExchange()
                .anyExchange()
                .authenticated()

                .and()

                .oauth2Login();

        return http.build();
    }
}
