package ru.planet.auth.helper;

import com.typesafe.config.Config;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@Getter
@Slf4j
public final class OpenApiProvider {

    private static final String RESOURCE_PATH = "openapi/planet-auth.yaml";

    private final String contract;
    private final boolean enabled;

    public OpenApiProvider(Config config) {
        enabled = config.hasPath("openapi.enabled") && config.getBoolean("openapi.enabled");
        var contextPath = config.hasPath("openapi.context-path")
                ? config.getString("openapi.context-path")
                : "";
        if (enabled) {
            contract = readContract(contextPath);
        } else {
            contract = "";
        }
    }

    private String readContract(String contextPath) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(RESOURCE_PATH)) {
            if (inputStream == null) {
                return "";
            } else {
                String contract = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                int openapiIndex = contract.indexOf("openapi:");
                int afterOpenapiNextIndex = contract.indexOf('\n', openapiIndex);

                return contract.substring(0, afterOpenapiNextIndex + 1) +
                        "\nservers:\n - url: " +
                        contextPath +
                        '\n' +
                        contract.substring(afterOpenapiNextIndex + 1);
            }
        } catch (IOException e) {
            log.warn("Unable to get openapi contract from resources", e);
            return "";
        }
    }

}
