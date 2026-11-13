public class Bulbasaur extends Pkmn{
    Bulbasaur(){

        name="bulbasaur";
        type="grass";
        checkMood();
    }
    Bulbasaur(String nname){
        this();
        setNickName(nname);
    }

    @Override
    public String speak(){
        return "bulba bulbasaur";
    }
}
