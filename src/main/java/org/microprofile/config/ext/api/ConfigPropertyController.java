package org.microprofile.config.ext.api;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/api")
public class ConfigPropertyController {

    @Inject
    @ConfigProperty(name = "test.property.key")
    private String testProperty;

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestProperty() {
        return testProperty;
    }
}


