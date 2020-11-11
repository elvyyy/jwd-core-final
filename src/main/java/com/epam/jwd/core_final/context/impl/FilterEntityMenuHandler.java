package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.List;
import java.util.Map;

public class FilterEntityMenuHandler implements EntityMenu {
    @Override
    public Object handleInput(Object o) {
        Map<String, Object> values = (Map<String, Object>) o;
        if (values.containsKey("crewMemberOptions")) {
            Role role = (Role) values.get("role");
            Rank rank = (Rank) values.get("rank");
            Boolean isReadyForNextMissions = (Boolean) values.get("isReadyForNextMissions");
            CrewMemberCriteria criteria = new CrewMemberCriteria();
            criteria.setRole(role);
            criteria.setRank(rank);
            criteria.setReadyForNextMission(isReadyForNextMissions);
            List<CrewMember> members = CrewServiceImpl.getInstance().findAllCrewMembersByCriteria(criteria);
            members.forEach(System.out::println);
        } else if (values.containsKey("spaceshipOptions")) {
            Long flightDistance = (Long) values.get("flightDistance");
            Boolean isReadyForNextMissions = (Boolean) values.get("isReadyForNextMissions");
            SpaceshipCriteria criteria = new SpaceshipCriteria();
            criteria.setFlightDistance(flightDistance)
                    .setReadyForNextMissions(isReadyForNextMissions);
            List<Spaceship> members = SpaceshipServiceImpl.getInstance().findAllSpaceshipsByCriteria(criteria);
            members.forEach(System.out::println);
        } else if (values.containsKey("missionOptions")) {
            String name = (String) values.get("name");
            Long distance = (Long) values.get("distance");
            MissionResult missionResult = (MissionResult) values.get("missionResult");
            FlightMissionCriteria criteria = new FlightMissionCriteria();
            criteria.setDistance(distance)
                    .setMissionResult(missionResult)
                    .setName(name);
            List<FlightMission> members = MissionServiceImpl.getInstance().findAllMissionsByCriteria(criteria);
            members.forEach(System.out::println);
        }
        return null;
    }

}
