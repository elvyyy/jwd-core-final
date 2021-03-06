package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Starting application...");
        ApplicationMenu menu = null;
        try {
            menu = Application.start();
            while (true) {
                try {
                    Long choice = (Long) menu.printAvailableOptions();
                    if (choice == 0) {
                        break;
                    }
                    menu.handleUserInput(choice);
                } catch (Exception e) {
                    log.error("Something went wrong", e);
                }
            }
        } catch (Exception e) {
            log.error("Cannot initialize app");
        }
        log.info("Finishing execution.");
    }
}