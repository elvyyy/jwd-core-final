package com.epam.jwd.core_final.domain;

import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private Map<Role, Short> crew;

    private Long flightDistance;

    private Boolean isReadyForNextMissions = true;

    public Spaceship(String name, Map<Role, Short> crew, Long flightDistance) {
        super(name);
        this.crew = crew;
        this.flightDistance = flightDistance;
    }

    public Spaceship() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spaceship)) return false;
        if (!super.equals(o)) return false;
        Spaceship spaceship = (Spaceship) o;
        return Objects.equals(crew, spaceship.crew) &&
                Objects.equals(flightDistance, spaceship.flightDistance) &&
                Objects.equals(isReadyForNextMissions, spaceship.isReadyForNextMissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), crew, flightDistance, isReadyForNextMissions);
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "crew=" + crew +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}' + super.toString();
    }

    public Boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }
}
