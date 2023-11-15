/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package equipments;

/**
 *
 * @author kaan
 */
public class Armor extends Equipment {

    private float defence;

    private Armor(String id, float money, float defence) {

        super(id, money);

        this.defence = defence;

    }

    public float getDefence() {

        return defence;

    }

    public static Armor createIronArmor() {

        return new Armor("1", 10, 3);

    }

    public static Armor createSteelArmor() {

        return new Armor("2", 15, 5);

    }

}
