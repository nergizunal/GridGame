package pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.*;

public class Game {

    private GameBoard gb;
    private String winner;
    private int ZordeNum;
    private int CallianceNum;
    public Game(){
        this.ZordeNum += 1;
        this.CallianceNum += 1;
        this.winner = null;
    }
    public void startGame(String inputFile){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            int size ;
            if(line.equals("BOARD")) {
                line = reader.readLine();
                size = Integer.parseInt(line.split("x")[0]);
            }
            else
                size = 0;
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
        this.gb.currentHPs();
    }
    public void makeMovements(String commandsFile) {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(commandsFile));
            String line = reader.readLine();
            Character c;
            System.out.println();
            ArrayList<Integer> moveX;
            ArrayList<Integer> moveY;
            while (line != null) {
                moveX = new ArrayList<>();
                moveY = new ArrayList<>();
                String[] t = line.split(" ");
                c = this.gb.getByName(t[0]);
                if(c == null)
                    continue;
                String[] points = t[1].split(";");
                if(points.length > 2*c.getMoveSteps()) {
                    System.out.println("Error : Move sequence contains wrong number of move steps. Input line ignored.");
                    break;
                }
                for(int i = 0; i<points.length; i +=2){
                    moveX.add(Integer.parseInt(points[i]));
                    moveY.add(Integer.parseInt(points[i + 1]));
                }
                this.move(c,moveX,moveY);
                this.gb.updateGrid();
                this.gb.printGrid();
                this.gb.currentHPs();
                if(this.isThereAWinner()){
                    System.out.println("Game Finished\n" + winner +" Wins");
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
            this.ZordeNum += 1;
        if(c instanceof CallianceCharacter)
            this.CallianceNum += 1;
    }
    public boolean isThereAWinner(){
        Iterator<Character> iter = this.gb.chars.iterator();
        int ZodecNum = 0;
        int CallianceNum = 0;
        while(iter.hasNext()){
            Character c = iter.next();
            if(c instanceof ZordeCharacter)
                ZodecNum +=1;
            else if(c instanceof CallianceCharacter)
                CallianceNum +=1;
        }
        if(ZodecNum == 0) {
            winner = "Calliance";
            return true;
        }
        else if(CallianceNum== 0) {
            winner = "Zorde";
            return true;
        }
        return false;

    }

    public void move( Character c, ArrayList<Integer> moveX, ArrayList<Integer> moveY){
        int x = c.getX();
        int y = c.getY();
        Iterator<Integer> iterX
                = moveX.iterator();
        Iterator<Integer> iterY
                = moveY.iterator();
        boolean fight = false;
        int last = 0;
        try {
            while (iterX.hasNext() && iterY.hasNext()) {
                    x += iterX.next();
                    y += iterY.next();
                    if(this.gb.grid[x][y] != null)
                        if(this.isAFellow(c,this.gb.grid[x][y]))
                            break;
                        else {
                            last += 1;
                            fight = true;
                            break;
                        }
                    else
                        last +=1;
            }

            if(last < moveX.size()){
                int i = last;
                while(i < moveX.size()) {
                    moveX.remove(i);
                    moveY.remove(i);
                    i++;
                }
                c.move(gb, moveX, moveY,false, fight);
            }
            c.move(gb, moveX, moveY,true, fight);
            c.setX(x);
            c.setY(y);
        }
        catch (IndexOutOfBoundsException IE){
            this.gb.printGrid();
            System.out.println("Error : Game board boundaries are exceeded. Input line ignored");
        }

    }
    public boolean isAFellow(Character c1, Character c2){
        if(c1 instanceof ZordeCharacter ){
            return c2 instanceof ZordeCharacter;
        }
        else return c2 instanceof CallianceCharacter;
    }

}
