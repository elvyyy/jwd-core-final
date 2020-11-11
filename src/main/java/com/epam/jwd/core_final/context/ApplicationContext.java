package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.BaseEntity;

import java.util.Collection;

public interface ApplicationContext {

    void init() throws Exception;

    <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass);

}
