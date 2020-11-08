package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Objects;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Long flightDistance;

    private Boolean isReadyForNextMissions;

    public SpaceshipCriteria setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
        return this;
    }

    public SpaceshipCriteria setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
        return this;
    }

    public <T extends Spaceship> boolean matches(T entity) {
        boolean result = super.matches(entity);
        if (Objects.nonNull(flightDistance)) {
            result &= flightDistance == entity.getFlightDistance();
        }
        if (Objects.nonNull(isReadyForNextMissions)) {
            result &= isReadyForNextMissions == entity.isReadyForNextMissions();
        }
        return result;
    }
}
