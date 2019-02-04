package org.microprofile.config.ext.configsource;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.jboss.logging.Logger;
import org.microprofile.config.ext.jpa.dao.ConfigPropertyDao;
import org.microprofile.config.ext.jpa.dao.impl.ConfigPropertyDaoImpl;
import org.microprofile.config.ext.mapper.ConfigPropertyMapper;
import org.microprofile.config.ext.mapper.type.ProfileType;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DbConfigSource implements ConfigSource {

    private static final String APPLICATION_CONFIG_KEY = "application";
    private static final String PROFILE_CONFIG_KEY = "profile";
    private static final String NAME = "DbConfigSource";
    private final Logger logger = Logger.getLogger(DbConfigSource.class);

    private ConfigPropertyDao configPropertyDao = new ConfigPropertyDaoImpl();

    private ConfigPropertyMapper configPropertyMapper = new ConfigPropertyMapper();

    private Map<String, String> configProperties;

    @Override
    public Map<String, String> getProperties() {
        checkAndReadConfigPropertiesFromDb();
        return configProperties;
    }

    private void checkAndReadConfigPropertiesFromDb() {
        System.out.println("Get the hell of a value for this haahahahahaaa-------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>yo man");
        if (configProperties == null) {

            String application;
            ProfileType profile;

            try {
                Config config = ConfigProvider.getConfig();
                application = config.getValue(APPLICATION_CONFIG_KEY, String.class);
                profile = config.getValue(PROFILE_CONFIG_KEY, ProfileType.class);

                if (application == null || profile == null) {
                    throw new RuntimeException();
                }
            } catch (Exception ex) {
                throw new RuntimeException("Error loading configuration properties from database, [application] or [profile] property not set, Please set [application] and [profile] properties either as JVM args, Environment variables or defining them in a file by the name (microprofile-config.properties)");
            }

            logger.infov("Fetching configuration from GlobalConfiguration for application = [{0}] and profile = [{1}]", application, profile);

            configProperties = configPropertyMapper.map(configPropertyDao.getConfigProperties(application, profile.toString()));

            logger.infov("Read configuration from GlobalConfiguration for application = [{0}] and profile = [{1}] : [{2}]", application, profile, configProperties
                    .entrySet()
                    .stream()
                    .map(e -> "[" + e.getKey() + " -> " + e.getValue() + "]")
                    .collect(Collectors.joining(",")));
        }
    }

    @Override
    public Set<String> getPropertyNames() {
        return configProperties.keySet();
    }

    @Override
    public int getOrdinal() {
        return 900;
    }

    @Override
    public String getValue(String key) {
        System.out.println("Get the hell of a value for this haahahahahaaa-------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        /*We do not want to read application name and profile from this config source
          as this will lead to StackOverFlow error due to implicit recursion.

          The properties [application] and [profile] can be set as:

          1- Environment variables (Highest precedence)
          2- JVM args
          3- Inside microprofile-config.properties file (Lowest precedence)

         */
        if (APPLICATION_CONFIG_KEY.equals(key) || PROFILE_CONFIG_KEY.equals(key)) {
            return null;
        }

        checkAndReadConfigPropertiesFromDb();

        if (configProperties.containsKey(key)) {
            return configProperties.get(key);
        }

        return null;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
