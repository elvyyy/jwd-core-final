package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class MissionEntityMenuHandler implements EntityMenu {
    private static final Logger log = LoggerFactory.getLogger(MissionEntityMenuHandler.class);

    @Override
    public Object handleInput(Object o) {
        System.out.print("Mission's name is ");
        String name = InputUtil.handleLine();
        System.out.println("When is your mission suppose to get started? ");
        System.out.printf("%s -> ", ApplicationProperties.getInstance().getDateTimeFormat());
        LocalDateTime startedAt = InputUtil.handleDate();
        System.out.println("When is your mission suppose to end? ");
        LocalDateTime endedAt = null;
        do {
            System.out.printf("%s -> ", ApplicationProperties.getInstance().getDateTimeFormat());
            endedAt = InputUtil.handleDate();
        } while (!endedAt.isAfter(startedAt));
        System.out.println("Distance: ");
        Long distance = InputUtil.handleChoice(1000, Integer.MAX_VALUE);
        FlightMission mission = MissionServiceImpl.getInstance().createMission(name, startedAt, endedAt, distance);
        SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
        try {
            for (Spaceship spaceship : spaceshipService.findAllSpaceships()) {
                spaceshipService.assignSpaceshipOnMission(spaceship, mission);
            }
        } catch (Exception e) {
            log.error("Spaceships already not capable to this mission", e);
        }

        CrewService crewService = CrewServiceImpl.getInstance();
        for (CrewMember crewMember : crewService.findAllCrewMembers()) {
            try {
                crewService.assignCrewMemberOnMission(crewMember, mission);
            } catch (Exception e) {
                log.error("This crewMember already not capable to this mission", e);
            }
        }

        return mission;
    }
}
