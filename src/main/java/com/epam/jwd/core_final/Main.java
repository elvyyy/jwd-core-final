package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.context.impl.NassaContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Starting application...");
        NassaContext instance;
        try {
            ApplicationMenu start = Application.start();
            instance = NassaContext.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Finishing execution.");
    }
}