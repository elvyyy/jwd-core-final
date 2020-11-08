package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private Long distance;

    private Spaceship assignedSpaceShift;

    private List<CrewMember> assignedCrew;

    private MissionResult missionResult;

    public FlightMissionCriteria setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    public FlightMissionCriteria setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
        return this;
    }

    public FlightMissionCriteria setDistance(Long distance) {
        this.distance = distance;
        return this;
    }

    public FlightMissionCriteria setAssignedSpaceShift(Spaceship assignedSpaceShift) {
        this.assignedSpaceShift = assignedSpaceShift;
        return this;
    }

    public FlightMissionCriteria setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
        return this;
    }

    public FlightMissionCriteria setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
        return this;
    }

    public <T extends FlightMission> boolean matches(T entity) {
        boolean result = super.matches(entity);
        if (Objects.nonNull(startedAt)) {
            result &= startedAt.equals(entity.getStartedAt());
        }
        if (Objects.nonNull(endedAt)) {
            result &= endedAt.equals(entity.getEndedAt());
        }
        if (Objects.nonNull(distance)) {
            result &= distance == entity.getDistance();
        }
        if (Objects.nonNull(assignedSpaceShift)) {
            result &= assignedSpaceShift.equals(entity.getAssignedSpaceship());
        }
        if (Objects.nonNull(assignedCrew)) {
            result &= assignedCrew.equals(entity.getAssignedCrew());
        }
        if (Objects.nonNull(missionResult)) {
            result &= missionResult == entity.getMissionResult();
        }
        return result;
    }
}
