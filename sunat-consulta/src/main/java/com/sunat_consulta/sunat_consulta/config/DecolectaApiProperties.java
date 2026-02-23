package com.sunat_consulta.sunat_consulta.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "decolecta.api")
public record DecolectaApiProperties(
        String baseUrl,
        String token,
        Integer connectTimeoutMs,
        Integer readTimeoutMs
) {
    public int resolvedConnectTimeoutMs() {
        return connectTimeoutMs == null ? 5000 : connectTimeoutMs;
    }

    public int resolvedReadTimeoutMs() {
        return readTimeoutMs == null ? 10000 : readTimeoutMs;
    }
}
