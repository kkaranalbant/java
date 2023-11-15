/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package equipments;

/**
 *
 * @author kaan
 */
public class Gun extends Equipment {

    private float damage;

    private Gun(String id, float money, float damage) {

        super(id, money);

        this.damage = damage;

    }

    public float getDamage() {

        return this.damage;

    }

    public static Gun createIronSword() {

        return new Gun("1", 5, 3);

    }

    public static Gun createSteelSword() {

        return new Gun("2", 9, 5);

    }

    public static Gun createSilverBow() {

        return new Gun("3", 7, 4);

    }

    public static Gun createElderBow() {

        return new Gun("4", 11, 6);

    }

    public static Gun createMagicalStick() {

        return new Gun("5", 8, (float) 4.5);

    }

    public static Gun createSuperMagicalStick() {

        return new Gun("6", 13, 7);

    }

}
