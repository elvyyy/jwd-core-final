package com.epam.jwd.core_final.domain;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties {

    private static ApplicationProperties instance = null;
    private String crewFileName;
    private String dateTimeFormat;

    //todo
    private Integer fileRefreshRate;
    private String inputRootDir;
    private String missionsFileName;
    private String outputRootDir;
    private String spaceshipsFileName;

    private ApplicationProperties() {
    }

    public static ApplicationProperties getInstance() {
        if (instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public void setCrewFileName(String crewFileName) {
        this.crewFileName = crewFileName;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public void setFileRefreshRate(Integer fileRefreshRate) {
        this.fileRefreshRate = fileRefreshRate;
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public void setInputRootDir(String inputRootDir) {
        this.inputRootDir = inputRootDir;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public void setMissionsFileName(String missionsFileName) {
        this.missionsFileName = missionsFileName;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public void setOutputRootDir(String outputRootDir) {
        this.outputRootDir = outputRootDir;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public void setSpaceshipsFileName(String spaceshipsFileName) {
        this.spaceshipsFileName = spaceshipsFileName;
    }
}
