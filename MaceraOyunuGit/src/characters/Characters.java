/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package characters;

import equipments.Armor;
import equipments.Gun;
import java.util.LinkedList;
import experience.Level;
import java.sql.ResultSet;
import java.sql.SQLException;
import queries.Queries ;

/**
 *
 * @author kaan
 */
public abstract class Characters {

    private int id;
    private String name;
    private float damage, health, money;
    private float resetDamage;
    private float defaultHp;
    private float defaultDamage;
    private float defence;
    private float defaultDefence;
    private Armor armor = null;
    private Gun gun = null;
    public static LinkedList<Characters> characters = new LinkedList();
    private final Level level = new Level();
    private int experience;
    private int maxExp = 30;

    void setId(int id) {

        this.id = id;

    }

    public int getId() {

        return id;

    }

    public void setDamage(float damage) {

        this.damage = damage;

    }

    void setResetDamage(float damage) {

        defaultDamage = damage;

    }

    public void setMoney(float money) {

        this.money = money;

    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;

    }

    public void setHp(float hp) {

        health = hp;

    }

    public float getHp() {

        return health;

    }

    public void refreshHp() {

        health = defaultHp;
        
        Queries query = new Queries () ;
        
        query.setHp(this);
        
        query = null ;

    }

    public float getDefence() {

        return defence;

    }

    public void setResetDamage() {

        damage = resetDamage;

    }

    public void setDefence(float defence) {

        this.defence = defence;

    }

    public void setGun(Gun gun) {

        this.gun = gun;

    }

    public void setArmor(Armor armor) {

        this.armor = armor;

    }

    public Gun getGun() {

        return gun;

    }

    public Armor getArmor() {

        return armor;

    }

    public float getDamage() {

        return this.damage;
    }

    public void setDefaultHp(float defaultHp) {

        this.defaultHp = defaultHp;

    }

    public float getDefaultHp() {

        return defaultHp;

    }

    public float getMoney() {

        return money;

    }

    public void resetDamage() {

        this.damage = defaultDamage;

    }

    public void setDefenceToDefaultDefence() {

        defence = defaultDefence;

    }

    public void setExperience(int exp) {

        experience = exp;

    }

    public int getExperience() {

        return experience;

    }

    public void setMaxExp(int value) {

        this.maxExp = value;

    }

    public int getMaxExp() {

        return maxExp;

    }

    public Level getLevel() {

        return level;

    }

    public float getDefaultDamage() {

        return this.damage;

    }

    public float getDefaultDefence() {

        return this.defaultDefence;

    }

    public void setDefaultDefence(float defaultDefence) {

        this.defaultDefence = defaultDefence;

    }
    
    public void setDefaultDamage (float defaultDamage) {
        
        this.defaultDamage = defaultDamage ;
        
    }

    public void display() {

        if (gun == null && armor == null) {

            System.out.println("Id : " + id + "\nCharacter Type : "
                    + MainCharacter.mainCharacter.toString() + "\nName : "
                    + name + "\nLevel : " + level.getLevel() + "\nDamage : " // problem burada . 
                    + damage + "\nHealth : " + health + "\nMoney : "
                    + money + "\nDefence : "
                    + defence +"\nPerk : "+MainCharacter.mainCharacter.getLevel().getPerk()+ "\n\n\n");

        } else if (gun != null && armor == null) {

            System.out.println("Id : " + id + "\nCharacter Type : "
                    + MainCharacter.mainCharacter.toString() + "\nName : "
                    + name + "\nLevel : " + level.getLevel() + "\nDamage : "
                    + damage + "\nHealth : " + health + "\nMoney : "
                    + money + "\nDefence : "
                    + defence + "\nGun ID : " + gun.getId() + "\nPerk : "+MainCharacter.mainCharacter.getLevel().getPerk()+"\n\n\n");

        } else if (gun == null && armor != null) {

            System.out.println("Id : " + id + "\nCharacter Type : "
                    + MainCharacter.mainCharacter.toString() + "\nName : " + name
                    + "\nLevel : " + level.getLevel() + "\nDamage : "
                    + damage + "\nHealth : " + health + "\nMoney : " + money
                    + "\nDefence : "
                    + defence + "\nArmor ID : " + armor.getId() + "\nPerk : "+MainCharacter.mainCharacter.getLevel().getPerk()+"\n\n\n");

        } else {

            System.out.println("Id : " + id + "\nCharacter Type : "
                    + MainCharacter.mainCharacter.toString()
                    + "\nName : " + name + "\nLevel : " + level.getLevel()
                    + "\nDamage : "
                    + damage + "\nHealth : " + health + "\nMoney : " + money
                    + "\nDefence : "
                    + defence + "\nGun Id : " + gun.getId() + "\nArmor ID : "
                    + armor.getId() + "\nPerk : "+MainCharacter.mainCharacter.getLevel().getPerk()+"\n\n\n");

        }

    }

    public void setXp(int xp) {

        experience = xp;

    }

    public static <T extends Characters> T createCharacterFromDb(T type, ResultSet result) {

        try {

            int id = result.getInt("id");
            String name = result.getString("name");
            float damage = result.getFloat("damage");
            float health = result.getFloat("health");
            float money = result.getFloat("money");
            float defaultHp = result.getFloat("defaultHp");
            float defaultDamage = result.getFloat("defaultDamage");
            float defence = result.getFloat("defence");
            float defaultDefence = result.getFloat("defaultDefence");
            int armorId = result.getInt("armorId");
            int gunId = result.getInt("gunId");
            int level = result.getInt("lev");
            int exp = result.getInt("exp");
            int maxExp = result.getInt("maxExp");
            int perk = result.getInt("perk");

            type.setId(id);
            type.setName(name);
            type.setDamage(damage);
            type.setHp(health);
            type.setMoney(money);
            type.setDefaultHp(defaultHp);
            type.setResetDamage(defaultDamage);
            type.setDefence(defence);
            type.setDefaultDefence(defaultDefence);
            type.getLevel().setLevel(level);
            type.getLevel().setPerk(perk);
            type.setXp(exp);
            type.setMaxExp(maxExp);

            if (armorId == 0 && gunId == 0) {

                return type;

            } else if (armorId != 0 && gunId == 0) {

                Armor ironArmor = Armor.createIronArmor();

                if (armorId == Integer.parseInt(ironArmor.getId())) {

                    type.setArmor(ironArmor);

                } else {

                    ironArmor = null;
                    Armor steelArmor = Armor.createSteelArmor();
                    type.setArmor(steelArmor);

                }

            } else if (armorId == 0 && gunId != 0) {

                Gun ironSword = Gun.createIronSword();
                Gun steelSword = Gun.createSteelSword();
                Gun silverBow = Gun.createSilverBow();
                Gun elderBow = Gun.createElderBow();
                Gun magicalStick = Gun.createMagicalStick();
                Gun sMagicalStick = Gun.createSuperMagicalStick();

                if (gunId == Integer.parseInt(ironSword.getId())) {

                    type.setGun(ironSword);
                    steelSword = null;
                    silverBow = null;
                    elderBow = null;
                    magicalStick = null;
                    sMagicalStick = null;

                } else if (gunId == Integer.parseInt(steelSword.getId())) {

                    type.setGun(steelSword);
                    ironSword = null;
                    silverBow = null;
                    elderBow = null;
                    magicalStick = null;
                    sMagicalStick = null;
                } else if (gunId == Integer.parseInt(silverBow.getId())) {
                    type.setGun(silverBow);
                    ironSword = null;
                    steelSword = null;
                    elderBow = null;
                    magicalStick = null;
                    sMagicalStick = null;
                } else if (gunId == Integer.parseInt(elderBow.getId())) {
                    type.setGun(elderBow);
                    ironSword = null;
                    steelSword = null;
                    silverBow = null;
                    magicalStick = null;
                    sMagicalStick = null;
                } else if (gunId == Integer.parseInt(magicalStick.getId())) {
                    type.setGun(magicalStick);
                    ironSword = null;
                    steelSword = null;
                    silverBow = null;
                    elderBow = null;
                    sMagicalStick = null;
                } else if (gunId == Integer.parseInt(sMagicalStick.getId())) {
                    type.setGun(sMagicalStick);
                    ironSword = null;
                    steelSword = null;
                    silverBow = null;
                    elderBow = null;
                    magicalStick = null;
                }

            } else {

                Armor ironArmor = Armor.createIronArmor();

                if (armorId == Integer.parseInt(ironArmor.getId())) {

                    type.setArmor(ironArmor);

                } else {

                    ironArmor = null;
                    Armor steelArmor = Armor.createSteelArmor();
                    type.setArmor(steelArmor);

                }

                Gun ironSword = Gun.createIronSword();
                Gun steelSword = Gun.createSteelSword();
                Gun silverBow = Gun.createSilverBow();
                Gun elderBow = Gun.createElderBow();
                Gun magicalStick = Gun.createMagicalStick();
                Gun sMagicalStick = Gun.createSuperMagicalStick();

                if (gunId == Integer.parseInt(ironSword.getId())) {

                    type.setGun(ironSword);
                    steelSword = null;
                    silverBow = null;
                    elderBow = null;
                    magicalStick = null;
                    sMagicalStick = null;

                } else if (gunId == Integer.parseInt(steelSword.getId())) {

                    type.setGun(steelSword);
                    ironSword = null;
                    silverBow = null;
                    elderBow = null;
                    magicalStick = null;
                    sMagicalStick = null;
                } else if (gunId == Integer.parseInt(silverBow.getId())) {
                    type.setGun(silverBow);
                    ironSword = null;
                    steelSword = null;
                    elderBow = null;
                    magicalStick = null;
                    sMagicalStick = null;
                } else if (gunId == Integer.parseInt(elderBow.getId())) {
                    type.setGun(elderBow);
                    ironSword = null;
                    steelSword = null;
                    silverBow = null;
                    magicalStick = null;
                    sMagicalStick = null;
                } else if (gunId == Integer.parseInt(magicalStick.getId())) {
                    type.setGun(magicalStick);
                    ironSword = null;
                    steelSword = null;
                    silverBow = null;
                    elderBow = null;
                    sMagicalStick = null;
                } else if (gunId == Integer.parseInt(sMagicalStick.getId())) {
                    type.setGun(sMagicalStick);
                    ironSword = null;
                    steelSword = null;
                    silverBow = null;
                    elderBow = null;
                    magicalStick = null;
                }

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        Characters.characters.add(type);

        return type;

    }

}
