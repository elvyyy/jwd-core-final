package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class JSONHandler implements EntityMenu {
    private static final Logger log = LoggerFactory.getLogger(JSONHandler.class);

    @Override
    public Object handleInput(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        ApplicationProperties properties = ApplicationProperties.getInstance();
        String path = "src/main/resources/" +
                properties.getOutputRootDir() + File.separator + properties.getMissionsFileName();
        File outputFile = new File(path);
        try {
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(outputFile, MissionServiceImpl.getInstance().findAllMissions());
        } catch (IOException e) {
            log.error("Cannot produce JSON file {}", path, e);
        }
        return path;
    }
}
