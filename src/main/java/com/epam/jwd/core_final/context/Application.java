package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;

import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws Exception {
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::getInstance;
        final NassaContext nassaContext = NassaContext.getInstance();

        nassaContext.init();
        return applicationContextSupplier::get;
    }
}
