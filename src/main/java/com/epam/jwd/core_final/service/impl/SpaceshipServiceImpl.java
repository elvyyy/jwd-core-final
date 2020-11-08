package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    @Override
    public List<Spaceship> findAllSpaceships() {
        return new ArrayList<>(NassaContext.getInstance()
                    .retrieveBaseEntityList(Spaceship.class));
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return NassaContext.getInstance()
                .retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(criteria::matches)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return NassaContext.getInstance()
                .retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(criteria::matches)
                .findAny();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember, FlightMission mission) throws RuntimeException {

    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        return null;
    }
}
