/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package characters;

import java.util.Stack;

/**
 *
 * @author kaan
 */
public class EnemyCharacter {

    private String name;
    private float damage;
    private float health;
    private float money;
    private float defence;
    private int xp ;

    public static Stack<EnemyCharacter> enemies = new Stack();

    private EnemyCharacter(String name, float damage, float health, float money, float defence , int xp) {

        this.name = name;
        this.damage = damage;
        this.health = health;
        this.money = money;
        this.defence = defence;
        this.xp = xp ;

    }

    public static void createZombie() {

        enemies.add(new EnemyCharacter("Zombie", 3, 10, 4, 2,5));

    }

    public static void createVampire() {

        enemies.add(new EnemyCharacter("Vampire", 4, 14, 7, 3,8));

    }

    public static void createGhost() {

        enemies.add(new EnemyCharacter("Ghost", 7, 20, 12, 5,11));

    }

    public float getMoney() {
        return money;
    }

    public float getHealth() {
        return health;
    }

    public float getDamage() {
        return damage;
    }

    public float getDefence() {
        return defence;
    }

    public void setHealth(float value) {
        health = value;
    }

    public String getName() {

        return name;

    }
    public int getXp () {
        
        return xp ; 
        
    }

}
