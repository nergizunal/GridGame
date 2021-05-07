package pack;

public class Human extends CallianceCharacter {
    public Human( String name){
        super( name);
        this.attackPoint = Constants.humanAP;
        this.hitPoint = 100;
        this.moveSteps = Constants.humanMaxMove;
    }
}

