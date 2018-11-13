/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.ui;

import java.util.Scanner;

/**
 *
 * @author bacag
 */
public class UserIOConsoleImpl implements UserIO {

    final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        double answer = getDouble(prompt);

        return answer;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double answer = 0;

        do {
            answer = getDouble(prompt);
        } while (answer < min || answer > max);

        return answer;
    }

    // Handles error checking for Double
    public double getDouble(String prompt) {
        boolean validNumber = false;
        double number = 0;

        while (!validNumber) {
            try {
                System.out.print(prompt + " ");
                String input = SCANNER.nextLine();
                number = Double.parseDouble(input);
                validNumber = true;

            } catch (NumberFormatException ex) {
            }
        }

        return number;
    }

    @Override
    public float readFloat(String prompt) {
        float answer = getFloat(prompt);

        return answer;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float answer = 0;

        do {
            answer = getFloat(prompt);
        } while (answer < min || answer > max);

        return answer;
    }

    // Handles error checking for Floats
    public float getFloat(String prompt) {
        boolean validNumber = false;
        float number = 0;

        while (!validNumber) {
            try {
                System.out.print(prompt + " ");
                String input = SCANNER.nextLine();
                number = Float.parseFloat(input);
                validNumber = true;

            } catch (NumberFormatException ex) {
            }
        }

        return number;
    }

    @Override
    public int readInt(String prompt) {
        int answer = getInt(prompt);

        return answer;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int answer = 0;

        do {
            answer = getInt(prompt);
        } while (answer < min || answer > max);

        return answer;
    }

    // Handles error checking for Ints
    public int getInt(String prompt) {
        boolean validNumber = false;
        int number = 0;

        while (!validNumber) {
            try {
                System.out.print(prompt + " ");
                String input = SCANNER.nextLine();
                number = Integer.parseInt(input);
                validNumber = true;

            } catch (NumberFormatException ex) {
            }
        }

        return number;
    }

    @Override
    public long readLong(String prompt) {
        long answer = getLong(prompt);

        return answer;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long answer = 0;

        do {
            answer = getLong(prompt);
        } while (answer < min || answer > max);

        return answer;
    }

    // Handles error checking for Longs
    public long getLong(String prompt) {
        boolean validNumber = false;
        long number = 0;

        while (!validNumber) {
            try {
                System.out.print(prompt + " ");
                String input = SCANNER.nextLine();
                number = Long.parseLong(input);
                validNumber = true;

            } catch (NumberFormatException ex) {
            }
        }

        return number;
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt + " ");
        String input = SCANNER.nextLine();

        return input;
    }
}
   
