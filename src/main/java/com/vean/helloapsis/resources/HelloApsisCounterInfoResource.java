package com.vean.helloapsis.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.vean.helloapsis.api.CounterInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by anve on 2016-12-06.
 *
 * This is a Resource Class responsible for the Counter's Information functionality:
 * allows to get a current value of a specified counter.
 *
 */

@Path("/counter-info")
@Produces(MediaType.APPLICATION_JSON)
public class HelloApsisCounterInfoResource {
    private final String template;
    private final String defaultName;
    private ConcurrentHashMap<String,LongAdder> countersStorage;

    public HelloApsisCounterInfoResource(String template, String defaultName, ConcurrentHashMap <String,LongAdder> countersStorage) {
        this.template = template;
        this.defaultName = defaultName;
        this.countersStorage = countersStorage;
    }

    @GET
    @Timed
    public CounterInfo getCounterInfo(@QueryParam("name") Optional<String> name) {
        final String content = String.format(template, name.or(defaultName));
        CounterInfo counterInfo;
        try {
            counterInfo = new CounterInfo(content, countersStorage.get(content).longValue());
        } catch (Exception e) {
            throw new WebApplicationException(404);
        }
        return counterInfo;
    }
}
