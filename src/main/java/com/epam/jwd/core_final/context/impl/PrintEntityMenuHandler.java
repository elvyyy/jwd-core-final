package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

public class PrintEntityMenuHandler implements EntityMenu {
    @Override
    public Object handleInput(Object o) {
        switch ((Integer) o) {
            case 1: {
                CrewServiceImpl.getInstance().findAllCrewMembers()
                        .forEach(System.out::println);
                break;
            }
            case 2: {
                SpaceshipServiceImpl.getInstance().findAllSpaceships()
                        .forEach(System.out::println);
                break;
            }
            case 3: {
                MissionServiceImpl.getInstance().findAllMissions()
                        .forEach(System.out::println);
                break;
            }
        }
        return null;
    }
}
