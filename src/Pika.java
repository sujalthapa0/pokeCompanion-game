public class Pika extends Pkmn{
    Pika(){

        name="Pika";
        type="electric";
        checkMood();

    }
    Pika(String nname){
        this();
        setNickName(nname);
    }

    @Override
    public String  speak(){
        return "Pika-Pika";
    }
}
