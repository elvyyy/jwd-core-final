package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
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

    private List<CrewMember> assignedCrew;
    private Spaceship assignedSpaceship;
    private Long distance;
    private LocalDateTime endedAt;
    private MissionResult missionResult;
    private LocalDateTime startedAt;

    @Override
    public <T extends BaseEntity> boolean matches(T entity) {
        boolean result = super.matches(entity);
        FlightMission member = (FlightMission) entity;
        if (Objects.nonNull(startedAt)) {
            result &= startedAt.equals(member.getStartedAt());
        }
        if (Objects.nonNull(endedAt)) {
            result &= endedAt.equals(member.getEndedAt());
        }
        if (Objects.nonNull(distance)) {
            result &= distance.equals(member.getDistance());
        }
        if (Objects.nonNull(assignedSpaceship)) {
            result &= assignedSpaceship.equals(member.getAssignedSpaceship());
        }
        if (Objects.nonNull(assignedCrew)) {
            result &= assignedCrew.equals(member.getAssignedCrew());
        }
        if (Objects.nonNull(missionResult)) {
            result &= missionResult.equals(member.getMissionResult());
        }
        return result;
    }

    public FlightMissionCriteria setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
        return this;
    }

    public FlightMissionCriteria setAssignedSpaceship(Spaceship assignedSpaceship) {
        this.assignedSpaceship = assignedSpaceship;
        return this;
    }

    public FlightMissionCriteria setDistance(Long distance) {
        this.distance = distance;
        return this;
    }

    public FlightMissionCriteria setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
        return this;
    }

    public FlightMissionCriteria setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
        return this;
    }

    public FlightMissionCriteria setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
        return this;
    }
}
