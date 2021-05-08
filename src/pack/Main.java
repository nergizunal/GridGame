package pack;
public class Main {
    public static void main(String [] args){
        Game gb = new Game();
        String i = args[0];
        String c = args[1];
        gb.startGame(i);
        gb.makeMovements(c);

    }
}
