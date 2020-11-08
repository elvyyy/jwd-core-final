package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.ApplicationProperties;

public interface FileInputReader {

    default void readAll() throws Exception {
        String rootFolder = "src/main/resources/" + ApplicationProperties.getInstance().getInputRootDir();
        read(rootFolder);
    }

    void read(String rootFolder) throws Exception;

}
