package com.company;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class GuessGame {
    public static int numberToGuess;
    public static int numberOfAttempts = 0;
    public static int points;
    public static int bestScore;
    public static int max;
    public static void play(int max_num) {
        max = max_num;
        numberToGuess = ThreadLocalRandom.current().nextInt(1, max + 1);
        showInputModal();
    }

    private static void processUserEntry(int num) {
        JFrame frame = new JFrame("GuessGame");
        numberOfAttempts = numberOfAttempts + 1;
        String message;
        if(num == numberToGuess) {
            points = Math.round(max / numberOfAttempts);
            if (points > bestScore){
                bestScore = points;
                message = String.format("Looks like we have a champ here! Epic, huh?\nYour score is %d points.", points);
            } else {
                message = String.format("So close, bro! The best score is %d points\nYour score is %d points", bestScore, points);
            }
            JOptionPane.showMessageDialog(frame, message);
            showRestartGame();
        } else if (num > numberToGuess){
            message = String.format("%d is bigger than the number you should guess.", num);
            JOptionPane.showMessageDialog(frame, message);
            showInputModal();
        } else {
            message = String.format("%d is lower than the number you should guess.", num);
            JOptionPane.showMessageDialog(frame, message);
            showInputModal();
        }
    }

    private static void showInputModal() {
        JFrame frame = new JFrame("GuessGame");
        String numStr = JOptionPane.showInputDialog(frame, "What's your number?");
        if (numStr != null) {
            int num = Integer.parseInt(numStr);
            processUserEntry(num);
        } else {
            showRestartGame();
        }
    }

    private static void showRestartGame(){
        int reply = JOptionPane.showConfirmDialog(null, "Restart game?", "Think twice!", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            System.out.printf("Game finished by the user. \n");
            System.out.printf("Best score is %i", bestScore);
            System.exit(0);
        } else {
            play(max);
        }
    }

}