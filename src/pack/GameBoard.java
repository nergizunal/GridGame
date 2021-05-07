package pack;

import java.util.HashSet;
import java.util.Iterator;

public class GameBoard {
    public int size;
    public HashSet<Character> chars = new HashSet<Character>();
    public Character grid[][];
    public GameBoard(int size){
        this.size = size;
        this.grid = new Character[size][size];
    }
    public void updateGrid(){
        for(int i = 0; i < this.size; i++)
            for(int j = 0; j < this.size; j++)
                this.grid[i][j] = null;
        for(Character c: this.chars)
            this.grid[c.getX()][c.getY()] = c;
    }
    public void printGrid(){
        for(int i = 0; i <= this.size + 1 ; i ++)
            System.out.print("**");
        System.out.println();

        for(int i = 0; i < this.size; i++){
            System.out.print("* ");
            for(int j = 0; j < this.size; j++)
                if(this.grid[j][i] == null)
                    System.out.print("  ");
                else
                    System.out.print(this.grid[j][i]);
            System.out.println(" *");
        }

        for(int i = 0; i <= this.size + 1 ; i ++)
            System.out.print("**");
    }
    public Character getByName(String name){
        Iterator<Character> iter
                = this.chars.iterator();
        while(iter.hasNext()){
            Character c= iter.next();
            if(c.name.equals(name))
                return c;
        }
        return null;
    }

}
