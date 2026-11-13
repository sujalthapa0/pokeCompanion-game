import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class numberGuessing {
    public static int numGuess() {
        JOptionPane.showMessageDialog(null,"you got 5 turns\n number is between 1 and 100");

        Random random = new Random();
        int rn = random.nextInt(100)+1;

        int turns = 5;

        while(turns>0){
            turns--;
            String input = JOptionPane.showInputDialog(null, "Guess (-1 to exit):\n(Turns left: " + (turns + 1) + ")");

            if(input==null) return 0;
            int guess;
            try {
                guess=Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }
            if(guess==-1){
                JOptionPane.showMessageDialog(null,"you lost!");
                return 0;
            }

            if(guess==rn){
                JOptionPane.showMessageDialog(null,"you won!");
                return turns;
            }
            if(guess>rn){
                JOptionPane.showMessageDialog(null,"guess is high.");
            }
            else if(guess<rn){
                JOptionPane.showMessageDialog(null,"guess is low");
            }
        }

        JOptionPane.showMessageDialog(null,"you ran out of turns!");
        return 0;
    }

}
