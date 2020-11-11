package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.EntityExistsInStorage;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {
    private static CrewServiceImpl instance = null;

    private CrewServiceImpl() {
    }

    public static CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return new ArrayList<>(NassaContext.getInstance()
                .retrieveBaseEntityList(CrewMember.class));
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        return NassaContext.getInstance()
                .retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(criteria::matches)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return NassaContext.getInstance()
                .retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(criteria::matches)
                .findAny();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember, Object... args) {
        String name = (String) args[0];
        Role role = (Role) args[1];
        Rank rank = (Rank) args[2];
        Boolean isReadyForNextMissions = (Boolean) args[3];
        crewMember.setName(name);
        crewMember.setRole(role);
        crewMember.setRank(rank);
        crewMember.setReadyForNextMissions(isReadyForNextMissions);
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember, FlightMission mission) throws RuntimeException {
        if (crewMember.isReadyForNextMissions()) {
            mission.addCrewMember(crewMember);
        }
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        if (NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class).add(crewMember)) {
            return crewMember;
        }
        return null;
    }

    @Override
    public CrewMember createCrewMember(Role role, String name, Rank rank) throws RuntimeException {
        CrewMember crewMember = CrewMemberFactory.getInstance().create(name, role, rank);
        if (NassaContext.getInstance()
                .retrieveBaseEntityList(CrewMember.class)
                .add(crewMember)) {
            return crewMember;
        }
        throw new EntityExistsInStorage("CrewMember already exists in the storage");
    }
}
