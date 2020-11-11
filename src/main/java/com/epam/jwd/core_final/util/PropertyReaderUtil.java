package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertyReaderUtil.class);

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    /**
     * try-with-resource using FileInputStream.
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties() throws IOException {
        final String propertiesFileName = "application.properties";
        String filePath = "src/main/resources/" + propertiesFileName;
        log.info("Loading properties {}", filePath);
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        }
        populateProperties();
        log.info("Properties are loaded successfully");
    }

    private static void populateProperties() {
        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
        String inputRootDir = properties.getProperty("inputRootDir");
        log.info("Input root directory is {}", inputRootDir);
        String outputRootDir = properties.getProperty("outputRootDir");
        log.info("Output root directory is {}", outputRootDir);
        String crewFileName = properties.getProperty("crewFileName");
        log.info("Crew file name is {}", crewFileName);
        String missionsFileName = properties.getProperty("missionsFileName");
        log.info("Mission's file name {}", missionsFileName);
        String spaceshipsFileName = properties.getProperty("spaceshipsFileName");
        log.info("Spaceship's file name {}", spaceshipsFileName);
        String fileRefreshRate = properties.getProperty("fileRefreshRate");
        log.info("File refresh rate is {}", fileRefreshRate);
        String dateTimeFormat = properties.getProperty("dateTimeFormat");
        log.info("Date-time format is {}", dateTimeFormat);
        applicationProperties.setInputRootDir(inputRootDir);
        applicationProperties.setOutputRootDir(outputRootDir);
        applicationProperties.setCrewFileName(crewFileName);
        applicationProperties.setMissionsFileName(missionsFileName);
        applicationProperties.setSpaceshipsFileName(spaceshipsFileName);
        applicationProperties.setFileRefreshRate(Integer.valueOf(fileRefreshRate));
        applicationProperties.setDateTimeFormat(dateTimeFormat);
    }

    public static Properties getProperties() {
        return properties;
    }
}
