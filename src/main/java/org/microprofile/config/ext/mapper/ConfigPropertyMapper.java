package org.microprofile.config.ext.mapper;

import org.microprofile.config.ext.jpa.model.ConfigProperty;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigPropertyMapper {

    public Map<String, String> map(List<ConfigProperty> globalConfigurations) {

        if (globalConfigurations == null || globalConfigurations.isEmpty()) {
            return Collections.emptyMap();
        }

        return globalConfigurations
                .stream()
                .collect(Collectors.toMap(ConfigProperty::getConfigKey, ConfigProperty::getConfigValue));

    }
}
