package pack;

import java.util.ArrayList;
import java.util.Iterator;

public class Human extends CallianceCharacter {
    public Human( String name){
        super(name);
        this.attackPoint = Constants.humanAP;
        this.hitPoint = 100;
        this.defaultHitPoints = 100;
        this.moveSteps = Constants.humanMaxMove;
    }
    @Override
    public void move(GameBoard gb, ArrayList<Integer> moveX, ArrayList<Integer> moveY, boolean isFinalized, boolean fight){
        Iterator<Integer> iterX
                = moveX.iterator();
        Iterator<Integer> iterY
                = moveY.iterator();
        while (iterX.hasNext() && iterY.hasNext()) { //iterate to end
            this.x += iterX.next();
            this.y += iterY.next();
        }
        if(fight) //if there is a enemy, fightForDeath
            this.fightForDeath(gb.grid[x][y]);
        else //else attack at last move
            this.attack(gb);

    }

}

