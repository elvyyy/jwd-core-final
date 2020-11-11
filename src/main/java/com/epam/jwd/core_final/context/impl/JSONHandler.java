package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.util.CriteriaUtil;
import com.epam.jwd.core_final.util.InputUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler implements EntityMenu {
    private static final Logger log = LoggerFactory.getLogger(JSONHandler.class);

    @Override
    public Object show(Object o) {
        List<FlightMission> missions = MissionServiceImpl.getInstance().findAllMissions();
        String line = null;
        if (!missions.isEmpty()) {
            System.out.println("Select missions separated by spaces");
            for (int i = 0; i < missions.size(); i++) {
                System.out.printf("%d - %s\n", i, missions.get(i).getName());
            }
            try {
                line = InputUtil.handleLine();
            } catch (Exception e) {
                log.error("Bad input error", e);
            }
        }
        return line;
    }

    @Override
    public Object handleInput(Object o) {
        List<Integer> choices = CriteriaUtil.separateArgs(o);
        ObjectMapper objectMapper = new ObjectMapper();
        ApplicationProperties properties = ApplicationProperties.getInstance();
        String path = "src/main/resources/" +
                properties.getOutputRootDir() + File.separator + properties.getMissionsFileName();
        File outputFile = new File(path);

        List<FlightMission> missions = MissionServiceImpl.getInstance().findAllMissions();
        List<FlightMission> toPrint = new ArrayList<>();
        for (Integer choice : choices) {
            if (choice > missions.size() - 1
                    || choice < 0) {
                log.error("Invalid choice argument");
                return null;
            }
            toPrint.add(missions.get(choice));
        }
        try {
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(outputFile, toPrint);
        } catch (IOException e) {
            log.error("Cannot produce JSON file {}", path, e);
        }
        return path;
    }
}
