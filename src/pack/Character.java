/*abstract class Character, which anchestor of all other Character types.
Contains abract methods move() and attack().
It is comparable on name */
package pack;

import java.util.ArrayList;
import java.util.Comparator;
abstract public class Character  {
    protected int hitPoint;
    protected int defaultHitPoints;
    protected int attackPoint;
    protected int moveSteps;
    protected String name;
    protected int x; //row position of character
    protected int y; //col positi0n of character
    //Constructor
    public Character(String name){
        this.name = name;
    }
    public static Comparator<Character> charComparator = new Comparator<Character>() {

        @Override
        public int compare(Character c1, Character c2) { //to add Character class comparability specialty, it will be used while printing characters after the grid
            return this.stringCompare(c1.name,c2.name);
        }

        public int stringCompare(String str1, String str2) //compares 2 string variables lexical
        {
            for (int i = 0; i < str1.length() &&
                    i < str2.length(); i++) {
                if ((int)str1.charAt(i) ==
                        (int)str2.charAt(i)) {
                    continue;
                }
                else
                    return (int)str1.charAt(i) -
                            (int)str2.charAt(i);

            }

            if (str1.length() < str2.length())
                return (str1.length()-str2.length());

            else if (str1.length() > str2.length())
                return (str1.length()-str2.length());

            else
                return 0;
        }
        public int compareTo(Character c1, Character c2){
            return c1.name.compareTo(c2.name);
        }
        };
    //to print Character on grid
    public String toString(){
        return this.name;
    }
    //it prints curent situation and default situation after print grid
    public String currentHP(){
        return this.name + "\t" + this.hitPoint + "\t(" +this.defaultHitPoints + ")" ;
    }
    abstract public void move(GameBoard gb,ArrayList<Integer> moveX, ArrayList<Integer> moveY, boolean isFinalized, boolean fight);
    abstract public void attack(GameBoard gb);
    //when two Characters encounters, Character with higher hitPoint kills the other one and loose hitPoint as much as killed one.
    //if they have equal hitPoints, both die.
    public boolean fightForDeath(Character c){
        if(c == null)
            return false;
        c.updateHitPoint(-1*this.attackPoint);
        if(c.hitPoint <= 0 || this.hitPoint <=0)
            return false;

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
    //after a attack, fight and heal(Ork) it updates hitPoint so that it will not exceed defaultHitPoint
    public void updateHitPoint(int hitPoint) {
        if((this.hitPoint + hitPoint) >= this.defaultHitPoints){
            this.hitPoint = this.defaultHitPoints;
        }
        else
            this.hitPoint += hitPoint;
    }
    //GETTERS
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
    public int getY() {
        return y;
    }
    //SETTERS
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

}

