package com.insurance.purchase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public  class FileDownloadException extends RuntimeException {
    public FileDownloadException(String message, Throwable cause) {
        super(message, cause);
    }
}