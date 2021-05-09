package pack;

import java.util.ArrayList;
import java.util.Iterator;

public class Ork extends ZordeCharacter {
    private int healPoints;
    public Ork( String name){
        super( name);

        this.attackPoint = Constants.orkAP;
        this.moveSteps = Constants.orkMaxMove;
        this.healPoints = Constants.orkHealPoints;
        this.hitPoint = 200;
        this.defaultHitPoints = 200;

    }
    public void heal(GameBoard gb){
        int startX = this.x - 1;
        int startY = this.y - 1;
        for(int i = startX; i <startX + 3; i++){
            for(int j = startY; j <startX + 3; j++){
                try {
                    if ( gb.grid[i][j] instanceof ZordeCharacter ) {
                        gb.grid[i][j].updateHitPoint(this.healPoints);
                    }
                }
                catch (IndexOutOfBoundsException IE){
                    System.out.print("");
                }
            }
        }
    }

    @Override
    public void move(GameBoard gb, ArrayList<Integer> moveX, ArrayList<Integer> moveY, boolean isFinalized, boolean fight){
        this.heal(gb);
        Iterator<Integer> iterX
                = moveX.iterator();
        Iterator<Integer> iterY
                = moveY.iterator();
        while (iterX.hasNext() && iterY.hasNext()) {
            this.x += iterX.next();
            this.y += iterY.next();
        }
        gb.updateGrid();
        if(fight)
            this.fightForDeath(gb.grid[x][y]);
        else
            this.attack(gb);
    }
}
