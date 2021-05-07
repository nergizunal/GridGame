package pack;

import java.util.HashSet;

public class GameBoard {
    public int size;
    public HashSet<java.lang.Character> chars = new HashSet<java.lang.Character>();
    public String grid[][];
    public GameBoard(int size){
        this.size = size;
        this.grid = new String[size][size];
    }
    public void initGrid(){
        for(int i = 0; i < this.size; i++)
            for(int j = 0; j < this.size; j++)
                this.grid[i][j] = "  ";
    }
    public void printGrid(){
        for(int i = 0; i <= this.size + 1 ; i ++)
            System.out.print("**");
        System.out.println();

        for(int i = 0; i < this.size; i++){
            System.out.print("* ");
            for(int j = 0; j < this.size; j++)
                System.out.print(this.grid[j][i]);
            System.out.println(" *");
        }

        for(int i = 0; i <= this.size + 1 ; i ++)
            System.out.print("**");
    }

}
