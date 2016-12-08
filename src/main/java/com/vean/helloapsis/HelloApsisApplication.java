package com.vean.helloapsis;

import com.vean.helloapsis.health.TemplateHealthCheck;
import com.vean.helloapsis.resources.HelloApsisCounterInfoResource;
import com.vean.helloapsis.resources.HelloApsisCountersStorageResource;
import com.vean.helloapsis.resources.HelloApsisIncrementCounterResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by anve on 2016-12-06.
 *
 * This is an Application Class.
 *
 */
public class HelloApsisApplication extends Application<HelloApsisConfiguration> {

    private ConcurrentHashMap<String,LongAdder> countersStorage;

    public ConcurrentHashMap<String, LongAdder> getCountersStorageInstance() {
        return countersStorage;
    }

    public static void main(String[] args) throws Exception {
        new HelloApsisApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-apsis";
    }

    @Override
    public void initialize(Bootstrap<HelloApsisConfiguration> bootstrap) {
        if (countersStorage == null) countersStorage = new ConcurrentHashMap<>();
    }

    @Override
    public void run(HelloApsisConfiguration configuration,
                    Environment environment) {

        final HelloApsisIncrementCounterResource helloApsisIncrementCounterResource = new HelloApsisIncrementCounterResource(
                configuration.getTemplate(),
                configuration.getDefaultName(),
                getCountersStorageInstance()
        );
        environment.jersey().register(helloApsisIncrementCounterResource);

        final HelloApsisCountersStorageResource helloApsisCountersStorageResource =
                new HelloApsisCountersStorageResource(getCountersStorageInstance());
        environment.jersey().register(helloApsisCountersStorageResource);

        final HelloApsisCounterInfoResource helloApsisCounterInfoResource = new HelloApsisCounterInfoResource(
                configuration.getTemplate(),
                configuration.getDefaultName(),
                getCountersStorageInstance()
        );
        environment.jersey().register(helloApsisCounterInfoResource);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }
}
