package com.mgp.web.appmods.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//        Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")
//                .filters(f-> f.addRequestHeader("PPHeader" , "Pp")
//                        .addRequestParameter("Parama","Paapi"))
//                .uri("http://httpbin.org:80");
        Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")
                .filters(f-> f.addRequestHeader("PPHeader" , "Pp")
                        .addRequestParameter("Parama","Paapi"))
                .uri("http://httpbin.org:80");
        // Ideally these routeFunctions are not separately written and maintained instead plugged within

        return builder.routes()
                .route( p -> p.path("/get")
                        .filters(f-> f.addRequestHeader("PPHeader" , "Pp")
                                .addRequestParameter("Parama","Paapi"))
                        .uri("http://httpbin.org:80"))
                .route( p -> p.path("/currency-exchange/**")
                        .filters(f-> f.addRequestHeader("PPHeader" , "Pp"))
                        .uri("lb://currency-exchange"))
                .route( p -> p.path("/currency-conversion/**")
                        .filters(f-> f.addRequestHeader("PPHeader" , "Pp"))
                        .uri("lb://currency-conversion"))
                .route( p -> p.path("/currency-conversion-feign/**")
                        .filters(f-> f.addRequestHeader("PPHeader" , "Pp"))
                        .uri("lb://currency-conversion"))
                .build(); // if there is no customization over the route path we can have it as is builder.routes().build();

    }
}
