package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.util.InputUtil;

import java.time.LocalDateTime;
import java.util.List;

public class MissionUpdateHandler implements EntityMenu {
    @Override
    public Object show(Object o) {
        List<FlightMission> missions = MissionServiceImpl.getInstance().findAllMissions();
        long choice = -1;
        if (!missions.isEmpty()) {
            for (int i = 0; i < missions.size(); i++) {
                System.out.printf("%d - %s\n", i, missions.get(i).getName());
            }
            choice = InputUtil.handleChoice(0, missions.size() - 1);
        }
        return choice;
    }

    @Override
    public Object handleInput(Object o) {
        long choice = (Long) o;
        if (choice == -1) {
            System.out.println("There's no missions, but you can create your own ^_-");
            return choice;
        }
        List<FlightMission> missions = MissionServiceImpl.getInstance().findAllMissions();
        FlightMission flightMission = missions.get((int)choice);

        System.out.print("Enter a new mission's name: ");
        String newName = InputUtil.handleLine();

        System.out.print("startedAt: ");
        LocalDateTime startedAt = InputUtil.handleDate();

        LocalDateTime endedAt;
        do {
            System.out.print("endedAt: ");
            endedAt = InputUtil.handleDate();
        } while (startedAt.isAfter(endedAt));

        System.out.print("Distance: ");
        long distance = InputUtil.handleChoice(1000, Integer.MAX_VALUE);

        System.out.println("Mission result");
        System.out.println("1 - CANCELLED");
        System.out.println("2 - FAILED");
        System.out.println("3 - PLANNED");
        System.out.println("4 - IN_PROGRESS");
        System.out.println("5 - COMPLETED");
        long result = InputUtil.handleChoice(1, 5);
        MissionResult missionResult = extractMissionResult(result);
        return MissionServiceImpl.getInstance()
                .updateFlightMissionDetails(flightMission, newName, startedAt, endedAt, distance, missionResult);
    }

    public static MissionResult extractMissionResult(long result) {
        MissionResult missionResult = null;
        if (result == 1) {
            missionResult = MissionResult.CANCELLED;
        } else if (result == 2) {
            missionResult = MissionResult.FAILED;
        } else if (result == 3) {
            missionResult = MissionResult.PLANNED;
        } else if (result == 4) {
            missionResult = MissionResult.IN_PROGRESS;
        } else if (result == 5) {
            missionResult = MissionResult.COMPLETED;
        }
        return missionResult;
    }
}
