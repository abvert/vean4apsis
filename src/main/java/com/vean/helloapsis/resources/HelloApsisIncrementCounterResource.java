package com.vean.helloapsis.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.vean.helloapsis.api.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by anve on 2016-12-06.
 *
 * This is a Resource Class responsible for the Counter incrementation functionality:
 * allows to increment a specified counter by 1.
 *
 */

@Path("/hello-apsis")
@Produces(MediaType.APPLICATION_JSON)
public class HelloApsisIncrementCounterResource {
    private final String template;
    private final String defaultName;
    private ConcurrentHashMap<String,LongAdder> countersStorage;

    public HelloApsisIncrementCounterResource(String template, String defaultName, ConcurrentHashMap <String,LongAdder> countersStorage) {
        this.template = template;
        this.defaultName = defaultName;
        this.countersStorage = countersStorage;
    }

    @GET
    @Timed
    public CounterInfo sayHelloAndIncrementCounter(@QueryParam("name") Optional<String> name) {
        final String content = String.format(template, name.or(defaultName));
        this.countersStorage.computeIfAbsent(content, k -> new LongAdder()).increment();
        return new CounterInfo(content, this.countersStorage.get(content).longValue());
    }
}
