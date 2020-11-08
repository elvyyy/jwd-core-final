package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.Objects;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMission;

    public CrewMemberCriteria setRank(Rank rank) {
        this.rank = rank;
        return this;
    }

    public CrewMemberCriteria setReadyForNextMission(Boolean readyForNextMission) {
        isReadyForNextMission = readyForNextMission;
        return this;
    }

    public CrewMemberCriteria setRole(Role role) {
        this.role = role;
        return this;
    }

    public <T extends CrewMember> boolean matches(T entity) {
        boolean result = super.matches(entity);
        if (Objects.nonNull(role)) {
            result &= role == entity.getRole();
        }
        if (Objects.nonNull(rank)) {
            result &= rank == entity.getRank();
        }
        if (Objects.nonNull(isReadyForNextMission)) {
            result &= isReadyForNextMission == entity.isReadyForNextMissions();
        }
        return result;
    }

}
