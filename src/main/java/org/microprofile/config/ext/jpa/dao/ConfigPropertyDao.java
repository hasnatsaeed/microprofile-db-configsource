package org.microprofile.config.ext.jpa.dao;

import org.microprofile.config.ext.jpa.model.ConfigProperty;

import java.util.List;

public interface ConfigPropertyDao {

    List<ConfigProperty> getConfigProperties(String application, String profile);
}
