package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.FileInputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class CrewFileInputReader implements FileInputReader {
    private static final Logger log = LoggerFactory.getLogger(CrewFileInputReader.class);

    @Override
    public void read(String rootFolder) throws Exception {
        String crewFileName = ApplicationProperties.getInstance().getCrewFileName();
        String filePath = rootFolder + File.separator + crewFileName;
        log.info("Loading crew file from {}", filePath);

        String rawLine;
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
             rawLine = stream
                    .filter(line -> !line.startsWith("#"))
                    .findFirst()
                    .orElseThrow(() -> new InvalidStateException("Cannot parse input file " + filePath));
        }
        Arrays.stream(rawLine.split(";"))
                .distinct()
                .map(line -> line.split(","))
                .forEach(crew -> CrewServiceImpl.getInstance()
                        .createCrewMember(
                                Role.resolveRoleById(Integer.valueOf(crew[0])),
                                crew[1],
                                Rank.resolveRankById(Integer.valueOf(crew[2]))));
        log.info("Loaded successfully");
    }
}
