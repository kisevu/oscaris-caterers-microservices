package com.oscaris.caterers.oscaris_service_api_gateway.routes;
/*
*
@author ameda
@project requisition
*
*/

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class Routes {


    @Value("${employee.management.url}")
    private String employeeManagementUrl;

    @Value("${invoicing.service.url}")
    private String invoicingServiceUrl;

    @Value("${customer.service.url}")
    private String customerServiceUrl;


    @Bean
    public RouterFunction<ServerResponse> employeeManagementRoute(){
        return GatewayRouterFunctions.route("employee-management")
                .route(RequestPredicates.path("/api/product/**"),
                        HandlerFunctions.http(employeeManagementUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("employeeManagementCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> employeeManagementSwaggerRoute() {
        return GatewayRouterFunctions.route("employee-management-swagger")
                .route(RequestPredicates.path("/aggregate/employee-management/v3/api-docs"),
                        HandlerFunctions.http(employeeManagementUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return GatewayRouterFunctions.route("invoicing-service")
                .route(RequestPredicates.path("/api/invoice/**"),
                        HandlerFunctions.http(invoicingServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("invoicingServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("invoicing-service-swagger")
                .route(RequestPredicates.path("/aggregate/invoicing-service/v3/api-docs"),
                        HandlerFunctions.http(invoicingServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("invoicingServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> customerServiceRoute(){
        return GatewayRouterFunctions.route("customer-service")
                .route(RequestPredicates.path("/api/customer/**"),
                        HandlerFunctions.http(customerServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("customerServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> customerServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("customer-service-swagger")
                .route(RequestPredicates.path("/aggregate/customer-service/v3/api-docs"),
                        HandlerFunctions.http(customerServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("customerServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute(){
        return GatewayRouterFunctions.route("fallbackRoute")
                .GET("/fallbackRoute",
                        request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("service is unavailable, pls try again later"))
                .build();
    }
}
