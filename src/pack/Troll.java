package pack;

public class Troll extends ZordeCharacter {
    public Troll(String name){
        super(name);
        this.attackPoint = Constants.trollAP;
        this.hitPoint = 150;
        this.moveSteps = Constants.trollMaxMove;
    }
}
