package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MissionService {

    List<FlightMission> findAllMissions();

    List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria);

    Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria);

    FlightMission updateFlightMissionDetails(FlightMission flightMission, String name, LocalDateTime startedAt,
                                             LocalDateTime endedAt, Long distance, MissionResult missionResult);

    FlightMission createMission(String name, LocalDateTime startedAt, LocalDateTime endedAt, Long distance);

    FlightMission createMission(FlightMission flightMission);
}
