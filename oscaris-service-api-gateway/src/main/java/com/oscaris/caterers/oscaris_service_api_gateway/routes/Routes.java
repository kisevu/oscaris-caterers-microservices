package com.oscaris.caterers.oscaris_service_api_gateway.routes;
/*
*
@author ameda
@project requisition
*
*/

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        return GatewayRouterFunctions.route("EMPLOYEE-MANAGEMENT")
                .route(RequestPredicates.path("/api/product/**"),
                        HandlerFunctions.http("http://localhost:8787"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return GatewayRouterFunctions.route("INVOICING-SERVICE")
                .route(RequestPredicates.path("/api/invoice/**"),
                        HandlerFunctions.http("http://localhost:9090"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute(){
        return GatewayRouterFunctions.route("CUSTOMER-SERVICE")
                .route(RequestPredicates.path("/api/customer/**"),
                        HandlerFunctions.http("http://localhost:7878"))
                .build();
    }
}
