package pack;
import java.lang.reflect.Field;
public class Main {
    public static void main(String [] args){
        Game gb = new Game();
        gb.startGame("initials1.txt");
        gb.makeMovements("commands.txt");

    }
}
