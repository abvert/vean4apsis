package com.vean.helloapsis;

import com.vean.helloapsis.api.CounterInfo;
import com.vean.helloapsis.resources.HelloApsisIncrementCounterResource;
import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by anve on 2016-12-08.
 */
public class HelloApsisIncrementCounterResourceTest {

    private HelloApsisIncrementCounterResource resource;

    @Before
    public void setup() {
        resource = new HelloApsisIncrementCounterResource("Hello, %s", "Stranger", new ConcurrentHashMap<>());
    }

    @Test
    public void counterIncrementsByOne() {
        CounterInfo result = resource.sayHelloAndIncrementCounter(Optional.of("Anna"));
        CounterInfo result2 = resource.sayHelloAndIncrementCounter(Optional.of("Anna2"));

        assertThat(result.counter).isEqualTo(result2.counter);
    }

    @Test
    public void absentNameContainsDefaultName() {
        CounterInfo result = resource.sayHelloAndIncrementCounter(Optional.<String>absent());
        assertThat(result.content).contains("Stranger");
    }

    @Test
    public void contentContainsName() {
        CounterInfo result = resource.sayHelloAndIncrementCounter(Optional.of("Anna"));
        assertThat(result.content).contains("Anna");
    }
}
