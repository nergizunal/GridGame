package pack;

abstract public class ZordeCharacter extends Character {
    public ZordeCharacter(String name){
        super(name);
    }
    @Override
    public boolean fightForDeath(Character c){
        try {
            c = (CallianceCharacter) c;
            super.fightForDeath(c);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
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

                }
            }
        }
    }
}
