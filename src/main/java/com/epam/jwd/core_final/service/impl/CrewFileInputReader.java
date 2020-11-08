package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.FileInputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CrewFileInputReader implements FileInputReader {
    private static final Logger log = LoggerFactory.getLogger(CrewFileInputReader.class);

    @Override
    public void read(String rootFolder) throws Exception {
        String crewFileName = ApplicationProperties.getInstance().getCrewFileName();
        String filePath = rootFolder + File.separator + crewFileName;
        log.info("Loading crew file from {}", filePath);

        // File.lines() stream must be closed
//        try(Stream<String> stream = Files.lines(Paths.get(filePath))) {
//            stream
//                    .filter(line -> !line.startsWith("#"))
//                    .findFirst()
//                    .map(line -> Arrays
//                            .stream(line.split(";"))
//                            .distinct()
//                            .map(constituents -> constituents.split(","))
//                    )
//                    .orElseThrow(() -> new InvalidStateException("Cannot parse input file " + filePath))
//                    .map(crewConstituents -> CrewServiceImpl.getInstance().createCrewMember(
//                        Role.resolveRoleById(Integer.valueOf(crewConstituents[0])),
//                        crewConstituents[1],
//                        Rank.resolveRankById(Integer.valueOf(crewConstituents[2]))
//                    ))
//                    .forEach(CrewServiceImpl.getInstance()::createCrewMember);
//        }
        String[] lines;
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
             lines = stream
                    .filter(line -> !line.startsWith("#"))
                    .findFirst()
                    .map(line -> line.split(";"))
                    .orElseThrow(() -> new InvalidStateException("Cannot parse input file " + filePath));
        }

        log.info("Loaded successfully");
    }
}
