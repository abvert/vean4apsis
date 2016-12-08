package com.vean.helloapsis.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.vean.helloapsis.api.CounterInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by anve on 2016-12-06.
 *
 * This is a Resource Class responsible for the Counters Storage information functionality:
 * allows to get a list of all counters and their current value.
 *
 */

@Path("/counters-storage")
@Produces(MediaType.APPLICATION_JSON)
public class HelloApsisCountersStorageResource {
    private ConcurrentHashMap<String,LongAdder> countersStorage;

    public HelloApsisCountersStorageResource(ConcurrentHashMap <String,LongAdder> countersStorage) {
        this.countersStorage = countersStorage;
    }

    @GET
    @Timed
    public CounterInfo[] getCountersStogareInfo() {
        if (!countersStorage.isEmpty()) {
            CounterInfo[] counterInfoList = new CounterInfo[countersStorage.size()];
            ArrayList<CounterInfo> counterInfoArrayList = new ArrayList<CounterInfo>();
            for (String key : countersStorage.keySet()) {
                counterInfoArrayList.add(new CounterInfo(key, countersStorage.get(key).longValue()));
            }
            return counterInfoArrayList.toArray(counterInfoList);
        } else return new CounterInfo[0];
    }
}
