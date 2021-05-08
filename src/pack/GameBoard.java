package pack;

import java.util.*;

public class GameBoard {
    public int size;
    public HashSet<Character> chars = new HashSet<Character>();
    public Character grid[][];
    public GameBoard(int size){
        this.size = size;
        this.grid = new Character[size][size];
    }
    public void updateGrid(){

        this.updateChars();
        for(int i = 0; i < this.size; i++)
            for(int j = 0; j < this.size; j++)
                this.grid[i][j] = null;
        for(Character c: this.chars)
            this.grid[c.getX()][c.getY()] = c;
    }
    public void updateChars(){
        Iterator<Character> iter = this.chars.iterator();
        Character c;
        ArrayList<Character> rm = new ArrayList<Character>();
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
        System.out.println();
    }
    public void currentHPs(){
        ArrayList<Character> arr = this.sortChars();
        Iterator<Character> iter = arr.iterator();
        System.out.println("\n");
        while(iter.hasNext()){
            System.out.println(iter.next().currentHP());
        }
        System.out.println();
    }
    public ArrayList<Character> sortChars(){
        ArrayList<Character> arr = new ArrayList<>(this.chars);
        Collections.sort(arr,Character.charComparator);
        return arr;
    }
    public Character getByName(String name){
        Iterator<Character> iter = this.chars.iterator();
        while(iter.hasNext()){
            Character c= iter.next();
            if(c.name.equals(name))
                return c;
        }
        return null;
    }

}
