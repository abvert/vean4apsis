package com.vean.helloapsis.api;

/**
 * Created by anve on 2016-12-07.
 *
 * This is a Representation Class for the Counter's Information.
 *
 */
public class CounterInfo {
    public final long counter;

    public final String content;

    public CounterInfo(String content, long counter) {
        this.content = content;
        this.counter = counter;
    }
}
