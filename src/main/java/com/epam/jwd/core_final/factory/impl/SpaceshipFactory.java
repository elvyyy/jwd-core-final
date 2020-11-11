package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    private static final SpaceshipFactory instance = new SpaceshipFactory();

    private SpaceshipFactory(){
    }

    public static SpaceshipFactory getInstance() {
        return instance;
    }

    @Override
    public Spaceship create(Object... args) {
        String name = (String) args[0];
        Map<Role, Short> crew = (Map<Role, Short>) args[1];
        Long flightDistance = (Long) args[2];
        return new Spaceship(name, crew, flightDistance);
    }
}
