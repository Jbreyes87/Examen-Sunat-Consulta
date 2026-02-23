package com.sunat_consulta.sunat_consulta.config;

import com.sunat_consulta.sunat_consulta.exception.ProviderException;
import feign.RequestInterceptor;
import feign.Request;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class SunatFeignConfig {

    @Autowired
    private DecolectaApiProperties properties;

    @Bean
    public RequestInterceptor sunatAuthInterceptor() {
        return requestTemplate ->
                requestTemplate.header("Authorization", "Bearer " + properties.token());
    }

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(
                properties.resolvedConnectTimeoutMs(), TimeUnit.MILLISECONDS,
                properties.resolvedReadTimeoutMs(),    TimeUnit.MILLISECONDS,
                true
        );
    }

    @Bean
    public ErrorDecoder sunatErrorDecoder() {
        return (methodKey, response) -> {
            try (InputStream body = response.body().asInputStream()) {
                String json = new String(body.readAllBytes(), StandardCharsets.UTF_8);
                String message = extractMessage(json);
                return new ProviderException(message, response.status());
            } catch (Exception ex) {
                return new ProviderException("Error del proveedor SUNAT", response.status());
            }
        };
    }

    private String extractMessage(String json) {
        int idx = json.indexOf("\"message\"");
        if (idx == -1) return "Error del proveedor SUNAT";
        int colon = json.indexOf(":", idx);
        int q1    = json.indexOf("\"", colon);
        int q2    = json.indexOf("\"", q1 + 1);
        if (q1 == -1 || q2 == -1) return "Error del proveedor SUNAT";
        return json.substring(q1 + 1, q2);
    }
}
