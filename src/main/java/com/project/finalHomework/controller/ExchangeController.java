package com.project.finalHomework.controller;

import com.project.finalHomework.model.Exchange;
import com.project.finalHomework.service.ExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExchangeController {
    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/get/exchange/{code}")
    @Operation(summary = "Get exchange from NBP API", description = "Enter number of days, the return value will be mean" +
                                                                    " and all of the information from json will be saved in database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved and saved")
    @ApiResponse(responseCode = "404", description = "Invalid code")
    @ApiResponse(responseCode = "400", description = "Exteeded 250 days")
    public ResponseEntity<Double> getExchange(@PathVariable String code, @RequestParam(required = false) Integer numberOfDays) {
        if (numberOfDays != null) {
            return ResponseEntity.ok(exchangeService.getExchange(code, numberOfDays));
        } else {
            return ResponseEntity.ok(exchangeService.getExchange(code));
        }
    }
}
