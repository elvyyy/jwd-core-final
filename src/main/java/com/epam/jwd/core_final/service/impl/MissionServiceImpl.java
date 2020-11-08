package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {
    @Override
    public List<FlightMission> findAllMissions() {
        return new ArrayList<>(NassaContext.getInstance()
                    .retrieveBaseEntityList(FlightMission.class));
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return NassaContext.getInstance()
                .retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(criteria::matches)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return NassaContext.getInstance()
                .retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(criteria::matches)
                .findAny();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        return null;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        return null;
    }
}
