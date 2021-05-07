package pack;

public class Elf extends CallianceCharacter {
    private int  rangedAP;
    public Elf(String name){
        super(name);
        this.attackPoint = Constants.elfAP;
        this.moveSteps = Constants.elfMaxMove;
        this.rangedAP = Constants.elfRangedAP;
        this.hitPoint = 70;
    }
    public int getRangedAP(){
        return this.rangedAP;
    }

}
