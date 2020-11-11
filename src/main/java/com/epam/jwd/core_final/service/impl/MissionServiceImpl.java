package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.exception.EntityExistsInStorage;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {
    private static final MissionServiceImpl instance = new MissionServiceImpl();
    private static final Logger log = LoggerFactory.getLogger(MissionServiceImpl.class);

    private MissionServiceImpl() {
    }

    public static MissionServiceImpl getInstance() {
        return instance;
    }

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
    public FlightMission updateFlightMissionDetails(FlightMission flightMission, String name, LocalDateTime startedAt,
                                                    LocalDateTime endedAt, Long distance,
                                                    MissionResult missionResult) {
        log.info("Updating {} by {}, {}, {}, {}, {}", flightMission, name, startedAt,
                endedAt, distance, missionResult);
        flightMission.setName(name);
        flightMission.setDistance(distance);
        flightMission.setMissionResult(missionResult);
        flightMission.setStartedAt(startedAt);
        flightMission.setEndedAt(endedAt);
        return flightMission;
    }

    @Override
    public FlightMission createMission(String name, LocalDateTime startedAt, LocalDateTime endedAt, Long distance) {
        FlightMission mission = FlightMissionFactory.getInstance().create(name, startedAt, endedAt, distance);
        log.info("Trying add a mission {} to the storage", mission);
        if (NassaContext.getInstance()
                .retrieveBaseEntityList(FlightMission.class)
                .add(mission)) {
            log.info("Successfully added");
            return mission;
        }
        throw new EntityExistsInStorage("Spaceship already exists in the storage");
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        return null;
    }
}
