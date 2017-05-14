package de.nazaruk.routes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.springframework.core.env.CommandLinePropertySource.DEFAULT_NON_OPTION_ARGS_PROPERTY_NAME;

@Component
public class FileSource {

    @Autowired
    private Environment environment;

    public String getFileName() {
        return environment.getProperty(DEFAULT_NON_OPTION_ARGS_PROPERTY_NAME, String[].class)[0];
    }

}
