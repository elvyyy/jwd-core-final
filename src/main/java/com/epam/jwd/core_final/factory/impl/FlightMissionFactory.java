package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDateTime;

public class FlightMissionFactory implements EntityFactory {
    private static final FlightMissionFactory instance = new FlightMissionFactory();

    private FlightMissionFactory() {
    }

    public static FlightMissionFactory getInstance() {
        return instance;
    }

    @Override
    public FlightMission create(Object... args) {
        String missionsName = (String) args[0];
        LocalDateTime startDate = (LocalDateTime) args[1];
        LocalDateTime endDate = (LocalDateTime) args[2];
        Long distance = (Long) args[3];
        return new FlightMission(missionsName, startDate, endDate, distance);
    }
}
