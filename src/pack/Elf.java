package pack;

import java.util.ArrayList;
import java.util.Iterator;

public class Elf extends CallianceCharacter{
    private int  rangedAP;
    public Elf(String name){
        super(name);
        this.attackPoint = Constants.elfAP;
        this.moveSteps = Constants.elfMaxMove;
        this.rangedAP = Constants.elfRangedAP;
        this.hitPoint = 70;
        this.defaultHitPoints = 70;
    }
    public void attackRangedAP(GameBoard gb){
        int startX = this.x - 2;
        int startY = this.y - 2;

        for(int i = startX; i <startX + 5; i++){
            for(int j = startY; j <startY + 5; j++){
                try {
                    if ( gb.grid[i][j] instanceof ZordeCharacter)
                        gb.grid[i][j].updateHitPoint(-1*this.attackPoint);
                }
                catch (IndexOutOfBoundsException IE){

                }
            }
        }
    }
    @Override
    public void move(GameBoard gb, ArrayList<Integer> moveX, ArrayList<Integer> moveY, boolean isFinalized, boolean fight){
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
        if(isFinalized) {
           this.attackRangedAP(gb);
        }
        else {
            if(fight)
                this.fightForDeath(gb.grid[x][y]);
            else
                this.attack(gb);
        }

    }
    public void attack(GameBoard gb){
        int startX = this.x - 1;
        int startY = this.y - 1;
        for(int i = startX; i <startX + 3; i++){
            for(int j = startY; j <startY + 3; j++){
                try {
                    if ( gb.grid[i][j] instanceof Ork || gb.grid[i][j] instanceof Troll || gb.grid[i][j] instanceof Goblin)
                        gb.grid[i][j].updateHitPoint(-1 * this.getAttackPoint());
                }
                catch (IndexOutOfBoundsException IE){

                }
            }
        }
    }

}
