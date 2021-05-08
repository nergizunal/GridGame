package pack;

import java.util.ArrayList;
import java.util.Comparator;
abstract public class Character  {
    protected int hitPoint;
    protected int defaultHitPoints;
    protected int attackPoint;
    protected int moveSteps;
    protected String name;
    protected int x;
    protected int y;

    public Character(String name){
        this.name = name;
    }
    public static Comparator<Character> charComparator = new Comparator<Character>() {

        @Override
        public int compare(Character c1, Character c2) {
            return this.stringCompare(c1.name,c2.name);
        }
        public int stringCompare(String str1,
                                        String str2)
        {
            for (int i = 0; i < str1.length() &&
                    i < str2.length(); i++) {
                if ((int)str1.charAt(i) ==
                        (int)str2.charAt(i)) {
                    continue;
                }
                else {
                    return (int)str1.charAt(i) -
                            (int)str2.charAt(i);
                }
            }


            if (str1.length() < str2.length()) {
                return (str1.length()-str2.length());
            }
            else if (str1.length() > str2.length()) {
                return (str1.length()-str2.length());
            }


            else {
                return 0;
            }
        }
        public int compareTo(Character c1, Character c2){
            return c1.name.compareTo(c2.name);
        }
        };
    public String toString(){
        return this.name;
    }
    public String currentHP(){
        return this.name + "\t" + this.hitPoint + "\t(" +this.defaultHitPoints + ")" ;
    }
    abstract public void move(GameBoard gb,ArrayList<Integer> moveX, ArrayList<Integer> moveY, boolean isFinalized, boolean fight);
    abstract public void attack(GameBoard gb);
    public boolean fightForDeath(Character c){
        if(c == null)
            return false;
        c.updateHitPoint(-1*this.attackPoint);
        if(c.hitPoint > this.hitPoint){
            c.updateHitPoint(-1*this.hitPoint);
            this.updateHitPoint(-1*this.hitPoint);
        }
        else {
            this.updateHitPoint(-1*c.hitPoint);
            c.updateHitPoint(-1*c.hitPoint);
        }
        return true;
    }
    public int getHitPoint() {
        return hitPoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getMoveSteps() {
        return moveSteps;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void updateHitPoint(int hitPoint) {
        if((this.hitPoint + hitPoint) >= this.defaultHitPoints){
            this.hitPoint = this.defaultHitPoints;
        }
        else
            this.hitPoint += hitPoint;
    }
}

