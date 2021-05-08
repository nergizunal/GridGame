package pack;

abstract public class CallianceCharacter extends Character  {
    public CallianceCharacter( String name){
        super(name);
    }
    @Override
   public boolean fightForDeath(Character c){
        try {
            c = (ZordeCharacter) c;
            super.fightForDeath(c);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
