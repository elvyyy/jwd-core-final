package com.epam.jwd.core_final.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDateTime}
 * end date {@link java.time.LocalDateTime}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {
    private List<CrewMember> assignedCrew;
    private Spaceship assignedSpaceship;
    private Long distance;
    private LocalDateTime endedAt;
    private MissionResult missionResult;
    // todo
    private LocalDateTime startedAt;

    public FlightMission(String name, LocalDateTime startedAt, LocalDateTime endedAt,
                         Long distance) {
        super(name);
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.distance = distance;
        this.missionResult = MissionResult.PLANNED;
        assignedCrew = new ArrayList<>();
    }

    public boolean addCrewMember(CrewMember crewMember) {
        boolean result = false;
        if (!crewMember.isReadyForNextMissions()) {
            return result;
        }
        Role role = crewMember.getRole();
        Short limit = assignedSpaceship.getCrew().get(role);
        if (limit != null) {
            long count = assignedCrew.stream()
                    .filter(cm -> cm.getRole() == role)
                    .count();
            if (count < limit) {
                result = assignedCrew.add(crewMember);
                crewMember.setReadyForNextMissions(false);
            }
        }
        return result;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public Spaceship getAssignedSpaceship() {
        return assignedSpaceship;
    }

    public void setAssignedSpaceship(Spaceship assignedSpaceship) {
        this.assignedSpaceship = assignedSpaceship;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                "startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", distance=" + distance +
                ", assignedSpaceship=" + assignedSpaceship +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                '}' + super.toString();
    }
}
