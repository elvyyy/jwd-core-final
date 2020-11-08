package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.FileInputReader;
import com.epam.jwd.core_final.service.impl.CrewFileInputReader;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {
    private static NassaContext instance = null;

    // no getters/setters for them
    private final Collection<CrewMember> crewMembers = new ArrayList<>();
    private final Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<FlightMission> flightMissions = new ArrayList<>();

    private NassaContext() {
    }

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass == CrewMember.class) {
            return (Collection<T>) crewMembers;
        } else if (tClass == Spaceship.class) {
            return (Collection<T>) spaceships;
        }
        return null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws Exception {
        PropertyReaderUtil.loadProperties();
        FileInputReader fileInputReader = new CrewFileInputReader();
        fileInputReader.readAll();
    }

    public static void main(String[] args) {
        NassaContext context = new NassaContext();
        Collection<CrewMember> crewMembers = context.retrieveBaseEntityList(CrewMember.class);
        Collection<Spaceship> spaceships = context.retrieveBaseEntityList(Spaceship.class);
        if (crewMembers != null && spaceships != null) {
            System.out.println("Hello world");
        }
    }
}
