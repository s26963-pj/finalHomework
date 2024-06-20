package com.project.finalHomework.service;

import com.project.finalHomework.exceptions.ExchangeNotFoundInNbpException;
import com.project.finalHomework.exceptions.notValidNumberOfDays;
import com.project.finalHomework.model.Exchange;
import com.project.finalHomework.model.Rate;
import com.project.finalHomework.repository.ExchangeRepository;
import com.project.finalHomework.repository.RateRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

@Service
public class ExchangeService {

    private final RestTemplate restTemplate;
    private final ExchangeRepository exchangeRepository;
    private final RateRepository rateRepository;
    private final String format = "?format=json";

    public ExchangeService(RestTemplate restTemplate, ExchangeRepository exchangeRepository, RateRepository rateRepository) {
        this.restTemplate = restTemplate;
        this.exchangeRepository = exchangeRepository;
        this.rateRepository = rateRepository;
    }

    public Double getExchange(String code, Integer numberOfDays) {
        try {
            Exchange exchange = restTemplate.getForObject("https://api.nbp.pl/api/exchangerates/rates/a/" + code + "/last/" +
                    numberOfDays + format, Exchange.class);
            exchangeRepository.save(exchange);
            return getExchangeMean(exchange);
        } catch (HttpClientErrorException ex) {
            if (numberOfDays > 250) {
                throw new notValidNumberOfDays("Invalid number of days. Request cannot expand 250 days");
            }
            throw new ExchangeNotFoundInNbpException("Exchange with that code has not been found");
        }

    }

    public Double getExchange(String code) {
        Exchange exchange = restTemplate.getForObject("https://api.nbp.pl/api/exchangerates/rates/a/" + code + format, Exchange.class);
        exchangeRepository.save(exchange);
        return getExchangeMean(exchange);
    }

    private Double getExchangeMean(Exchange exchange) {
        Double total = 0.0;
        for (Rate rate : exchange.getRate()) {
            total += rate.getMid();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.0000");
        Double mean = total / exchange.getRate().size();
        mean = Double.parseDouble(decimalFormat.format(mean));
        return mean;
    }
}
