package com.sunat_consulta.sunat_consulta.exception;

public class ProviderException extends RuntimeException {

    private final int statusCode;

    public ProviderException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
