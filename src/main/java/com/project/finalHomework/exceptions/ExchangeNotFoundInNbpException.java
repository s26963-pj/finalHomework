package com.project.finalHomework.exceptions;

import org.springframework.web.client.RestClientException;

public class ExchangeNotFoundInNbpException extends RuntimeException {
    public ExchangeNotFoundInNbpException(String msg) {
        super(msg);
    }
}
