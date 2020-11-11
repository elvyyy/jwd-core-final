package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityAlreadyAssigned;
import com.epam.jwd.core_final.exception.EntityExistsInStorage;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    private static final Logger log = LoggerFactory.getLogger(SpaceshipServiceImpl.class);
    private static SpaceshipServiceImpl instance = null;

    private SpaceshipServiceImpl() {
    }

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }

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
    public Spaceship updateSpaceshipDetails(Spaceship spaceship, Long flightDistance,
                                            Boolean isReadyForNextMissions, Map<Role, Short> crew) {
        spaceship.setReadyForNextMissions(isReadyForNextMissions);
        spaceship.setFlightDistance(flightDistance);
        spaceship.setCrew(crew);
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship, FlightMission mission) throws RuntimeException {
        Objects.requireNonNull(spaceship);
        Objects.requireNonNull(mission);
        if (!spaceship.isReadyForNextMissions()) {
            return;
        }
        if (mission.getAssignedSpaceship() != null) {
            throw new EntityAlreadyAssigned("Mission already has a spaceship or not capable");
        }
        mission.setAssignedSpaceship(spaceship);
        spaceship.setReadyForNextMissions(false);
    }

    @Override
    public Spaceship createSpaceship(String name, Map<Role, Short> crew, Long flightDistance) throws RuntimeException {
        Spaceship spaceship = SpaceshipFactory.getInstance().create(name, crew, flightDistance);
        log.info("Trying add a spaceship {} to the storage", spaceship);
        if (NassaContext.getInstance()
                .retrieveBaseEntityList(Spaceship.class)
                .add(spaceship)) {
            log.info("Successfully added");
            return spaceship;
        }
        throw new EntityExistsInStorage("Spaceship already exists in the storage");
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        return null;
    }
}
