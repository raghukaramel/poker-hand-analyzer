package com.quicken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for /health endpoint.
 * This endpoint may be used to checking the health status.
 *
 * NOTE:
 * There is a better framework called Spring Boot Actuator available to implement healthcheck and lot more.
 * This may be replaced by Spring Boot Actuator module if more exhaustive healthcheck functionality needed in future.
 *
 * @author Raghu Karamel
 */
@RestController // makes this a REST Controller class
@RequestMapping("/health") // set API Path Prefix as /health
public class HealthCheckController {

    /**
     * Method for processing health status request and return current health status.
     * Right now, this method always return a healthy status without checking the actual status.
     *
     * Spring Boot Actuator module can be used to perform more exhaustive health checks.
     */
    @GetMapping
    public ResponseEntity<?> getHealthStatus(){
        return ResponseEntity.ok("All happy and running!");
    }

}
