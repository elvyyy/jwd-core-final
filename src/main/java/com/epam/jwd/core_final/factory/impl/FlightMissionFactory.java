package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;

public class FlightMissionFactory implements EntityFactory {
    @Override
    public FlightMission create(Object... args) {
        String name = (String) args[0];
        Long distance = (Long) args[1];
        // todo
        return null;
    }
}
