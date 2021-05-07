package pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.*;

public class Game {

    private GameBoard gb;
    private int ZodecNum;
    private int CallianceNum;
    private String winner;
    public Game(){
        this.ZodecNum = 0;
        this.CallianceNum = 0;
        this.winner = null;
    }
    public void startGame(String inputFile){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            line = reader.readLine();
            int size = Integer.parseInt(line.split("x")[0]);
            gb = new GameBoard(size);
            line = reader.readLine();
            String [] s;
            while(line != null) {
                if(line.equals("CALLIANCE") || line.equals("ZORDEC") ) {
                    while (line != null){
                        s = line.split(" ");
                        if(s[0].equals("ELF")){
                            Character c  = new Elf(s[1]);
                            c.setX(Integer.parseInt(s[2]));
                            c.setY(Integer.parseInt(s[3]));
                            this.addChar(c);
                        }
                        if(s[0].equals("DWARF")){
                            Character c  = new Dwarf(s[1]);
                            c.setX(Integer.parseInt(s[2]));
                            c.setY(Integer.parseInt(s[3]));
                            this.addChar(c);
                        }
                        if(s[0].equals("HUMAN")){
                            Character c  = new Human(s[1]);
                            c.setX(Integer.parseInt(s[2]));
                            c.setY(Integer.parseInt(s[3]));
                            this.addChar(c);
                        }
                        if(s[0].equals("GOBLIN")){
                            Character c  = new Goblin(s[1]);
                            c.setX(Integer.parseInt(s[2]));
                            c.setY(Integer.parseInt(s[3]));
                            this.addChar(c);
                        }
                        if(s[0].equals("TROLL")){
                            Character c  = new Troll(s[1]);
                            c.setX(Integer.parseInt(s[2]));
                            c.setY(Integer.parseInt(s[3]));
                            this.addChar(c);
                        }
                        if(s[0].equals("ORK")){
                            Character c  = new Ork(s[1]);
                            c.setX(Integer.parseInt(s[2]));
                            c.setY(Integer.parseInt(s[3]));
                            this.addChar(c);
                        }
                        line = reader.readLine();
                    }

                }
                line = reader.readLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.gb.updateGrid();
        this.gb.printGrid();
    }
    public void makeMovements(String commandsFile) {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(commandsFile));
            String line = reader.readLine();
            String[] s;
            Character c;
            System.out.println();
            ArrayList<Integer> moveX ;
            ArrayList<Integer> moveY;
            while (line != null) {
                moveX = new ArrayList<Integer>();
                moveY = new ArrayList<Integer>();
                String[] t = line.split(" ");
                c = this.gb.getByName(t[0]);
                if(c == null)
                    continue;
                String[] points = t[1].split(";");
                if(points.length > 2*c.getMoveSteps())
                    break;
                for(int i = 0; i<points.length; i +=2){
                    moveX.add(Integer.parseInt(points[i]));
                    moveY.add(Integer.parseInt(points[i + 1]));
                }
                this.move(c,moveX,moveY);
                this.gb.updateGrid();
                this.gb.printGrid();
                if(this.winner != null){
                    System.out.println("Winner:" + winner );
                    break;
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addChar(Character c){
        this.gb.chars.add(c);
        if(c instanceof ZordeCharacter)
            this.ZodecNum +=1;
        if(c instanceof CallianceCharacter)
            this.CallianceNum +=1;
    }
    public void removeChar(Character c){
        if(c instanceof ZordeCharacter)
            if(this.ZodecNum ==1)
                this.winner = "Calliance";
        if(c instanceof CallianceCharacter)
            if(this.CallianceNum == 1);
                this.winner = "Zordec";
        if(this.gb.chars.contains(c))
            this.gb.chars.remove(c);
    }

    public void move( Character c, ArrayList<Integer> moveX, ArrayList<Integer> moveY){
        int x = c.getX();
        int y = c.getY();
        Iterator<Integer> iterX
                = moveX.iterator();
        Iterator<Integer> iterY
                = moveY.iterator();
        boolean br = false;
        if(c instanceof Ork) {
            try {
                this.heal((Ork)c);
            }
             catch (Exception e) {
                System.out.print("");
            }
        }
        try {
            while (iterX.hasNext()) {
                while (iterY.hasNext()) {
                    x += iterX.next();
                    y += iterY.next();
                    if ((this.gb.grid[x][y] instanceof CallianceCharacter && c instanceof CallianceCharacter)
                            || this.gb.grid[x][y] instanceof ZordeCharacter && c instanceof ZordeCharacter) {
                        br = true;
                        break;
                    }
                    this.attack(c);
                }
                if (br)
                    break;
            }
        }
        catch (IndexOutOfBoundsException IE){
            this.gb.printGrid();
            System.out.println("Error : Game board boundaries are exceeded. Input line ignored");
        }
        c.setX(x);
        c.setY(y);


    }

    public void heal(Ork c){
        int startX = c.getX() - 1;
        int startY = c.getY() - 1;
        for(int i = startX; i <startX + 2; i++){
            for(int j = startY; j <startX + 2; j++) {
                if(this.gb.grid[i][j] instanceof ZordeCharacter) {
                    try {
                        this.gb.grid[i][j].updateHitPoint(c.getHealPoints());
                    }
                    catch(IndexOutOfBoundsException IE){
                        System.out.print("");
                    }
                }
            }
        }
    }
    public void attack(Character c){
        int startX = c.getX() - 1;
        int startY = c.getY() - 1;
        for(int i = startX; i <startX + 2; i++){
            for(int j = startY; j <startX + 2; j++){
                try {
                    if ((this.gb.grid[i][j] instanceof CallianceCharacter && c instanceof ZordeCharacter) || (this.gb.grid[i][j] instanceof ZordeCharacter && c instanceof CallianceCharacter)) {
                        this.gb.grid[i][j].updateHitPoint(-1 * c.getAttackPoint());
                        if (this.gb.grid[i][j].getHitPoint() <= 0)
                            this.gb.chars.remove(this.gb.grid[i][j]);


                    }
                }
                catch (IndexOutOfBoundsException IE){
                    System.out.print("");
                }
            }
        }
    }


    public void currentBoard(){
        this.gb.printGrid();
    }
}
