package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
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

    public <T extends BaseEntity> boolean matches(T entity) {
        boolean result = super.matches(entity);
        Spaceship member = (Spaceship) entity;
        if (Objects.nonNull(flightDistance)) {
            result &= flightDistance.equals(member.getFlightDistance());
        }
        if (Objects.nonNull(isReadyForNextMissions)) {
            result &= isReadyForNextMissions.equals(member.isReadyForNextMissions());
        }
        return result;
    }
}
