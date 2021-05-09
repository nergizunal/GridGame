package pack;

import java.util.*;

public class GameBoard {
    public int size;
    public HashSet<Character> chars = new HashSet<>();
    public Character[][] grid;
    public GameBoard(int size){
        this.size = size;
        this.grid = new Character[size][size];
    }
    public void updateGrid(){

        this.updateChars();
        for(int i = 0; i < this.size; i++)
            for(int j = 0; j < this.size; j++)
                this.grid[i][j] = null;

        for (Character c : this.chars)
            this.grid[c.getX()][c.getY()] = c;

    }
    public void updateChars(){
        Iterator<Character> iter = this.chars.iterator();
        Character c;
        ArrayList<Character> rm = new ArrayList<>();
        while(iter.hasNext()){
            c = iter.next();
            if(c != null)
                if (c.getHitPoint() <= 0)
                    rm.add(c);
        }
        iter = rm.iterator();
        while(iter.hasNext()){
            this.chars.remove(iter.next());
        }
    }
    public String printGrid(){
        String output = "";
        for(int i = 0; i <= this.size + 1 ; i ++)
            output += "**";
        output += "\n";

        for(int i = 0; i < this.size; i++){
            output += "* ";
            for(int j = 0; j < this.size; j++)
                if(this.grid[j][i] == null)
                    output += "  ";
                else
                    output += this.grid[j][i];
            output += " *\n";
        }

        for(int i = 0; i <= this.size + 1 ; i ++)
            output += "**";
        output += "\n";
        return output;
    }
    public String currentHPs(){
        ArrayList<Character> arr = this.sortChars();
        Iterator<Character> iter = arr.iterator();
        String output = "";
        output += "\n";
        while(iter.hasNext()){
            output += iter.next().currentHP() + "\n";
        }
        output += "\n";
        return output;
    }
    public ArrayList<Character> sortChars(){
        ArrayList<Character> arr = new ArrayList<>(this.chars);
        arr.sort(Character.charComparator);
        return arr;
    }
    public Character getByName(String name){
        for (Character c : this.chars) {
            if (c.name.equals(name))
                return c;
        }
        return null;
    }
}
