public class Charmender extends Pkmn{
    Charmender(){

        name="Charmender";
        type="fire";
        checkMood();

    }
    Charmender(String nname){
        this();
        setNickName(nname);
    }

    @Override
    public String speak(){
        return "char-char";
    }
}
