package com.vean.helloapsis.health;

import com.codahale.metrics.health.HealthCheck;
/**
 * Created by anve on 2016-12-06.
 *
 * This is a template Health Check which checks for two things:
 * that the provided template is actually a well-formed format string,
 * and that the template actually produces output with the given name.
 *
 */
public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String content = String.format(template, "TEST");
        if (!content.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
