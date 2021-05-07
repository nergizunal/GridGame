package pack;

public class Character {
    protected int hitPoint;
    protected int attackPoint;
    protected int moveSteps;
    public Character(int hitPoint, int attackPoint){
        this.hitPoint = hitPoint;
        this.attackPoint = attackPoint;
    }
    public boolean isDead(){
        return hitPoint <= 0;
    }
    

}
