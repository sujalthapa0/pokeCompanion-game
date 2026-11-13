import javax.swing.*;
import java.util.Random;

public class stonePaperScissorsGame {
    public static int stonePaperScissors(){
        Random random = new Random();
        // 1 for stone , 2 for paper , 3 for scissors
        int sps = random.nextInt(3) + 1;

        String input = JOptionPane.showInputDialog(null,
                "Stone paper scissors game\n Enter 1 for stone.\n Enter 2 for paper.\n Enter 3 for scissors.");

        if (input == null) return 0; // User canceled

        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input");
            return 0;
        }

        switch(choice){
            case 1:
                if(sps==1){
                    JOptionPane.showMessageDialog(null,"Draw!\n You both chose stone");
                    return 2; // Fixed from 1
                }
                else if(sps==2){
                    JOptionPane.showMessageDialog(null,"You lost!\n You chose stone, system chose paper.");
                    return 1; // Fixed from 2
                }
                else if(sps==3){
                    JOptionPane.showMessageDialog(null,"You won!\n You chose stone and system chose scissors");
                    return 3;
                }

            case 2:
                if(sps==1){
                    JOptionPane.showMessageDialog(null,"You won!\n You chose paper and system chose stone");
                    return 3;
                }
                else if(sps==2){
                    JOptionPane.showMessageDialog(null,"Draw!\n You both chose paper");
                    return 2; // Fixed from 1
                }
                else if(sps==3){
                    JOptionPane.showMessageDialog(null,"You lost!\n You chose paper, system chose scissors.");
                    return 1; // Fixed from 2
                }

            case 3:
                if(sps==1){
                    JOptionPane.showMessageDialog(null,"You lost!\n You chose scissors, system chose stone.");
                    return 1; // Fixed from 2
                }
                else if(sps==2){
                    JOptionPane.showMessageDialog(null,"You won!\n You chose scissors and system chose paper");
                    return 3;
                }
                else if(sps==3){
                    JOptionPane.showMessageDialog(null,"Draw!\n You both chose scissors");
                    return 2; // Fixed from 1
                }

            default:
                JOptionPane.showMessageDialog(null,"Invalid input");
                return 0;
        }
    }
}
