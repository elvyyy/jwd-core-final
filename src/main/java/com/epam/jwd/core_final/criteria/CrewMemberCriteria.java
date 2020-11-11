package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.Objects;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private Boolean isReadyForNextMission;
    private Rank rank;
    private Role role;

    @Override
    public <T extends BaseEntity> boolean matches(T entity) {
        boolean result = super.matches(entity);
        CrewMember member = (CrewMember) entity;
        if (Objects.nonNull(role)) {
            result &= role == member.getRole();
        }
        if (Objects.nonNull(rank)) {
            result &= rank == member.getRank();
        }
        if (Objects.nonNull(isReadyForNextMission)) {
            result &= isReadyForNextMission.equals(member.isReadyForNextMissions());
        }
        return result;
    }

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

}
