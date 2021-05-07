package pack;

public class Dwarf extends CallianceCharacter {
    public Dwarf( String name){
        super(name);
        this.attackPoint = Constants.dwarfAP;
        this.moveSteps = Constants.dwarfMaxMove;
        this.hitPoint = 120;
    }
}
