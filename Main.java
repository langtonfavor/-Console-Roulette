package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<String> playerNames = new ArrayList<String>();
        Files.lines(Paths.get("/home/langton/Desktop/assessment/names.txt")).forEach(string -> {
            playerNames.add(string);
        });

        // Read player's details from the command line
        int numberToBetOn = -1;
        String letterToBetOn = "null";
        String player = args[0];
        Double amountToBet = Double.parseDouble(args[2]);
        if(isNumeric(args[1])) {
            numberToBetOn = Integer.parseInt(args[1]);
        } else {
            letterToBetOn = args[1];
        }

        Timer timer = new Timer();
        calculateScore(player, numberToBetOn ,letterToBetOn, amountToBet);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nPlease enter the amount you want to bet on");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("\nPlease enter what you want to bet one, either a number or EVEN ODD");
                String choice = scanner.nextLine();
                int newNumberToBetOn = -1;
                String newLetterToBetOn = "null";
                if(isNumeric(choice)) {
                    newNumberToBetOn = Integer.parseInt(choice);
                } else {
                    newLetterToBetOn = choice;
                }
                calculateScore(player, newNumberToBetOn ,newLetterToBetOn, amount);
            }
        }, 0, 5000);

    }

    public static void calculateScore(String player, int numberBetOn, String letterBetOn, double amountBet){
        double amountWon = 0.0;
        String outcome = "LOSE";
        Random random = new Random();
        int randomNumber = random.nextInt(36 - 0 + 1) + 0;
        Boolean isRandomNumberEven = (randomNumber % 2 == 0);
        if(numberBetOn == randomNumber) {
            amountWon = amountBet * 36;
            outcome = "WIN";
        } else if(letterBetOn.equals("EVEN") && isRandomNumberEven == true ){
            amountWon = amountBet * 2;
            outcome = "WIN";
        }  else if(letterBetOn.equals("ODD") && isRandomNumberEven != true ){
            amountWon = amountBet * 2;
            outcome = "WIN";
        };
        System.out.println("Number: " + randomNumber);
        System.out.println("Player          Bet       Outcome     Winnings");
        System.out.println("---");
        if(numberBetOn != -1){
            System.out.println(player + "      " + numberBetOn + "      " + outcome + "       " + amountWon);
        }else{
            System.out.println(player + "      " + letterBetOn + "      " + outcome + "       " + amountWon);
        }
    }

    public static boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.chars().allMatch(Character::isDigit);

    }
}
