package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.service.FileInputReader;

public class SpaceshipFileInputReader implements FileInputReader {
    @Override
    public void read(String rootFolder) throws Exception {
        String filename = ApplicationProperties.getInstance().getSpaceshipsFileName();
        String path = rootFolder
                + "/"
                + filename;

//        Files.lines(Paths.get(path))
//                .dropWhile(line -> line.startsWith("#"))
//                .


    }
}
