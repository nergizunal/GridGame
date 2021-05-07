package pack;

public class Ork extends ZordeCharacter {
    private int healPoints;
    public Ork( String name){
        super( name);

        this.attackPoint = Constants.orkAP;
        this.moveSteps = Constants.orkMaxMove;
        this.healPoints = Constants.orkHealPoints;
        this.hitPoint = 200;

    }
    public int getHealPoints(){
        return this.healPoints;
    }
}
