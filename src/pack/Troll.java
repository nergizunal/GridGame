package pack;

import java.util.ArrayList;
import java.util.Iterator;

public class Troll extends ZordeCharacter {
    public Troll(String name){
        super(name);
        this.attackPoint = Constants.trollAP;
        this.hitPoint = 150;
        this.defaultHitPoints = 150;
        this.moveSteps = Constants.trollMaxMove;
    }
    @Override
    public void move(GameBoard gb, ArrayList<Integer> moveX, ArrayList<Integer> moveY, boolean isFinalized, boolean fight){
        Iterator<Integer> iterX
                = moveX.iterator();
        Iterator<Integer> iterY
                = moveY.iterator();
        while (iterX.hasNext() && iterY.hasNext()) {
            this.x += iterX.next();
            this.y += iterY.next();

        }
        if(fight)
            this.fightForDeath(gb.grid[x][y]);
        else
            this.attack(gb);

    }
    @Override
    public void attack(GameBoard gb){
        int startX = this.x - 1;
        int startY = this.y - 1;
        for(int i = startX; i <startX + 3; i++){
            for(int j = startY; j <startY + 3; j++){
                try {
                    if ( gb.grid[i][j] instanceof CallianceCharacter)
                        gb.grid[i][j].updateHitPoint(-1 * this.getAttackPoint());

                }
                catch (IndexOutOfBoundsException IE){
                    System.out.print("");
                }
            }
        }
    }
}
