package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

import java.util.Objects;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private Long id;
    private String name;

    public Criteria<T> setId(Long id) {
        this.id = id;
        return this;
    }

    public Criteria<T> setName(String name) {
        this.name = name;
        return this;
    }

    public <T extends BaseEntity> boolean matches(T entity) {
        Objects.requireNonNull(entity);
        boolean result = true;
        if (Objects.nonNull(id)) {
            result &= id == entity.getId();
        }
        if (Objects.nonNull(name)) {
            result &= name.equals(entity.getName());
        }
        return result;
    }
}
