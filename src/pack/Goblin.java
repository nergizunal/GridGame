package pack;

public class Goblin extends ZordeCharacter {
    public Goblin( String name){
        super(name);
        this.attackPoint = Constants.goblinAP;
        this.hitPoint = 80;
        this.moveSteps = Constants.goblinMaxMove;
    }
}
