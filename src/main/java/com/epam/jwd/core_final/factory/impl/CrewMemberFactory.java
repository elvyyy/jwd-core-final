package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {
    private static final CrewMemberFactory instance = new CrewMemberFactory();

    private CrewMemberFactory(){
    }

    public static CrewMemberFactory getInstance() {
        return instance;
    }

    @Override
    public CrewMember create(Object... args) {
        String name = (String) args[0];
        Role role = (Role) args[1];
        Rank rank = (Rank) args[2];
        return new CrewMember(name, role, rank);
    }
}
