package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.FileInputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.jwd.core_final.domain.Role.resolveRoleById;

public class SpaceshipFileInputReader implements FileInputReader {
    private static final Logger log = LoggerFactory.getLogger(SpaceshipFileInputReader.class);

    @Override
    public void read(String rootFolder) throws Exception {
        String filename = ApplicationProperties.getInstance().getSpaceshipsFileName();
        String path = rootFolder + File.separator + filename;
        log.info("Parsing {} file", path);
        List<String> collect;
        log.info("Removing comments");
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            collect = stream
                    .filter(line -> !line.startsWith("#"))
                    .distinct()
                    .collect(Collectors.toList());
        }
        log.info("Removed");
        log.info("Parsing input data...");
        for (String line : collect) {
            String[] split = line.split(";");
            String name = split[0];
            Long distance = Long.valueOf(split[1]);
            String rawCrew = split[2].substring(1, split[2].length() - 1);
//            final Map<Role, Short> crew = Arrays.stream(rawCrew.split(","))
//                    //.map(rawLine -> rawLine.split(":"))
//                    .collect(
//                            Collectors.toMap(
//                                    key -> resolveRoleById(Integer.valueOf(),
//                                    value -> Short.valueOf((value.split(":"))[1]),
//                                    (l, r) -> {
//                                        throw new IllegalArgumentException("Duplicate keys " + l + "and " + r);
//                                    },
//                                    () -> new EnumMap(Rank.class)));
            Map<Role, Short> crew = new EnumMap<>(Role.class);
            for (String s : rawCrew.split(",")) {
                String[] chunks = s.split(":");
                Role key = resolveRoleById(Integer.valueOf(chunks[0]));
                Short value = Short.valueOf(chunks[1]);
                crew.merge(key,
                        value,
                        (l, r) -> {
                            throw new IllegalArgumentException("Duplicate keys " + l + "and " + r);
                        });
            }
            SpaceshipServiceImpl.getInstance().createSpaceship(name, crew, distance);
        }
        log.info("File was successfully parsed");
    }
}
