package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputUtil {
    private InputUtil() {
    }

    public static long handleChoice(int lowerBound, int upperBound) {
        boolean notOk = true;
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while (notOk) {
            try {
                choice = Integer.valueOf(scanner.nextLine());
                if (choice < lowerBound || choice > upperBound) {
                    System.out.printf("Lower and upper bounds: [%d, %d]\n", lowerBound, upperBound);
                    continue;
                }
                notOk = false;
            } catch (Exception e) {
                System.out.println("Oops... Something went wrong :(");
            }
            //scanner.close();
        }
        return choice;
    }

    public static String handleLine() {
        Scanner scanner = new Scanner(System.in);
        boolean notOk = true;
        String result = null;
        do {
            try {
                result = scanner.nextLine();
                notOk = false;
            } catch (Exception e) {
                notOk = true;
            }
        } while (notOk);
        return result;
    }

    public static LocalDateTime handleDate() {
        Scanner scanner = new Scanner(System.in);
        boolean notOk = true;
        LocalDateTime dateTime = null;
        do {
            try {
                String date = scanner.nextLine();
                String dateTimeFormat = ApplicationProperties.getInstance().getDateTimeFormat();
                dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateTimeFormat));
                notOk = false;
            } catch (Exception e) {
                System.out.printf("%s -> ", ApplicationProperties.getInstance().getDateTimeFormat());
                notOk = true;
            }
        } while (notOk);
        return dateTime;
    }
}
