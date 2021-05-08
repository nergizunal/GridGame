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
}
