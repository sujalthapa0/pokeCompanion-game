import javax.swing.*;

public class Squirtle extends Pkmn{

    Squirtle(){

        name="squirtle";
        type="water";
        checkMood();
    }
    Squirtle(String nname){
        this();
        setNickName(nname);
    }

    @Override
    public String speak(){
        return "squirtle squirtle";
    }
}
