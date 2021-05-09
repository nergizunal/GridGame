package pack;

import java.util.ArrayList;
import java.util.Iterator;

public class Dwarf extends CallianceCharacter {
    public Dwarf( String name){
        super(name);
        this.attackPoint = Constants.dwarfAP;
        this.moveSteps = Constants.dwarfMaxMove;
        this.hitPoint = 120;
        this.defaultHitPoints = 120;
    }
    @Override
    public void move( GameBoard gb,ArrayList<Integer> moveX, ArrayList<Integer> moveY, boolean isFinalized, boolean fight){
        int lastX = moveX.remove(moveX.size() -1);
        int lastY = moveY.remove(moveY.size() -1);
            Iterator<Integer> iterX
                    = moveX.iterator();
            Iterator<Integer> iterY
                    = moveY.iterator();
            while (iterX.hasNext() && iterY.hasNext()) {
                this.x += iterX.next();
                this.y += iterY.next();
                this.attack(gb);
            }
        this.x += lastX;
        this.y += lastY;
        if(fight)
            this.fightForDeath(gb.grid[x][y]);
        else
            this.attack(gb);
    }

}
