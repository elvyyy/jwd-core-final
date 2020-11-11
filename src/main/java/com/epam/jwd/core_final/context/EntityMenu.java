package com.epam.jwd.core_final.context;

import java.util.Scanner;

public interface EntityMenu {

    Object handleInput(Object o);

    default Object show(Object o) {
        boolean notOk = true;
        int choice = -1;
        while (notOk) {
            System.out.println("1 - crewMembers");
            System.out.println("2 - spaceships");
            System.out.println("3 - missions");
            Scanner scanner = new Scanner(System.in);
            try {
                choice = Integer.valueOf(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    continue;
                }
                notOk = false;
            } catch (Exception e) {
                System.out.println("Oops... Something went wrong :(");
            }
        }
        return choice;
    }
}
