import javax.swing.*;

public abstract class Pkmn {

    public String name;
    private String nickName;
    String type;

    private int hunger=100; // 100 is full and 0 is hungry

    void setHunger(int val){
        if(val>100){
            this.hunger=100;
        }
        else if(val<0){
            this.hunger=0;
        }
        else {
            this.hunger=val;
        }
    }
    public int getHunger(){
        return hunger;
    }

    private int energy=100; // 100 is full and 0 is tired

    void setEnergy(int val){
        if(val>100){
            this.energy=100;
        }
        else if(val<0){
            this.energy=0;
        }
        else {
            this.energy=val;
        }
    }
    public int getEnergy(){
        return energy;
    }

    private int fun=100;


    void setFun(int val){
        if(val>100){
            this.fun=100;
        }
        else if(val<0){
            this.fun=0;
        }
        else {
            this.fun=val;
        }
    }
    public int getFun(){
        return fun;
    }

    public abstract String speak();

    public void play(){

        if ("angry".equalsIgnoreCase(getMood())) {
            JOptionPane.showMessageDialog(null,getNickName() + " is too angry to play! Feed it first.");
            return;
        }

        if(getEnergy()<20){
            JOptionPane.showMessageDialog(null,getNickName()+" is tired.");
            return;
        }
        speak();

        String[] options = {"Stone Paper Scissors", "Number Guessing Game"};
        int choice = JOptionPane.showOptionDialog(null, "Choose game-", "Play",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        int fac=0;
        int fun=0;
        switch (choice){
            case 0:
                fac= stonePaperScissorsGame.stonePaperScissors();
                fun=5+fac*5;
                break;
            case 1:
                fac=numberGuessing.numGuess();
                fun=5*fac;
                break;

            default:
                System.out.println("invalid input!");
                return;

        }
        JOptionPane.showMessageDialog(null,fun+" fun points added!");

        setHunger(getHunger() -20);
        setEnergy(getEnergy()-20);
        setFun(getFun()+fun);
        checkMood();

    }

    public void feed(){
        if ("sad".equalsIgnoreCase(getMood())) {
            JOptionPane.showMessageDialog(null,getNickName() + " hesitated because it was feeling down," +
                    " but warmed up to you!");
        }

        if(getHunger()==100){
            JOptionPane.showMessageDialog(null,getNickName()+" is full");
            return;
        }
        if ("angry".equalsIgnoreCase(getMood())) {
            JOptionPane.showMessageDialog(null,getNickName() + " grumpily ate the food.");
        } else {
            JOptionPane.showMessageDialog(null,getNickName() + " enjoyed the food!");
        }
        setHunger(getHunger()+20);
        setEnergy(getEnergy()-2);
        checkMood();
    }

    public void sleep(){
        if((getMood().equalsIgnoreCase("angry")) || getMood().equalsIgnoreCase("hungry")){
            JOptionPane.showMessageDialog(null,getNickName()+" is too hungry to sleep");
            return;
        }

        if(getEnergy()>50){
            JOptionPane.showMessageDialog(null,getNickName()+ " is wide awake");
            return;
        }
        JOptionPane.showMessageDialog(null,getNickName()+" is sleeeping");
        setEnergy(100);
        setHunger(getHunger()-10);
        checkMood();
    }

    public void setNickName(String nName){
        nickName=nName;
        JOptionPane.showMessageDialog(null,name+" has been named " +getNickName());
    }

    public String getNickName(){
        if(nickName==null){
            return name;
        }
        return nickName;
    }

    private String mood;

    private void setMood(String m){
        this.mood=m;
    }
    public String getMood(){
        return mood;
    }
    public void checkMood(){
        if(getHunger()<=20){
            setMood("angry");
        }
        else if (getEnergy()<=30) {
            setMood("tired");
        }
        else if(getFun()<=20){
            setMood("sad");
        }
        else if (getHunger()<=50) {
            setMood("hungry");
        }

        else {
            setMood("happy");
        }
    }

    public void expressNeed() {
        checkMood();
        switch (getMood()) {
            case "angry" -> JOptionPane.showMessageDialog(null,getNickName() + " is furious! It is starving!");
            case "sad" -> JOptionPane.showMessageDialog(null,getNickName() + " looks lonely and wants to play.");
            case "hungry" -> JOptionPane.showMessageDialog(null,getNickName() + "'s tummy is rumbling...");
            case "tired" -> JOptionPane.showMessageDialog(null,getNickName() + " is yawning and needs a nap.");
            default -> JOptionPane.showMessageDialog(null,getNickName() + " is feeling happy and energetic!");
        }
    }

    public void tick() {
        setHunger(getHunger() - 2);
        setEnergy(getEnergy() - 2);
        setFun(getFun() - 1);
        checkMood();
    }

}
