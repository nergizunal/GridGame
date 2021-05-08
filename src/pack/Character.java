package pack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

public class Character {
    protected int hitPoint;
    protected int attackPoint;
    protected int moveSteps;
    protected String name;
    protected int x;
    protected int y;

    public Character(String name){
        this.name = name;
    }
    public boolean isDead(){
        return hitPoint <= 0;
    }

    public String toString(){
        return this.name;
    }
    public String currentHP(){
        return this.name + "-->" + this.hitPoint + "-->";
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
        this.hitPoint += hitPoint;
    }
    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public void setMoveSteps(int moveSteps) {
        this.moveSteps = moveSteps;
    }

    public void setName(String name) {
        this.name = name;
    }
}
