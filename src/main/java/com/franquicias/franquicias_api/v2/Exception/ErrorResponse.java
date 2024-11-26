package com.franquicias.franquicias_api.v2.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private String details;

    public ErrorResponse(String errorCode, String message, HttpStatus httpStatus) {
        this.message = message;
        this.statusCode = httpStatus.value();
        this.timestamp = LocalDateTime.now();
        this.details = errorCode;
    }
}
