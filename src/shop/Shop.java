/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shop;

import equipments.Armor;
import equipments.Gun;
import equipments.Equipment;
import characters.MainCharacter;
import queries.Queries ;

/**
 *
 * @author kaan
 */
public class Shop {

    public void buyWeapon(Gun gun) {
        
        if(MainCharacter.mainCharacter == null) {
            
            System.out.println("Once ana karakter seciniz.");
            return ;
            
        }

        if (moneyControl(gun) && MainCharacter.mainCharacter.getGun() != null) {

            MainCharacter.mainCharacter.resetDamage();

            System.out.println("Old weapon (ID : " + MainCharacter.mainCharacter.getGun().getId() + ") removed.");

            MainCharacter.mainCharacter.setGun(null); // for triggering gc

            MainCharacter.mainCharacter.setMoney(MainCharacter.mainCharacter.getMoney() - gun.getMoney());

            MainCharacter.mainCharacter.setDamage(MainCharacter.mainCharacter.getDamage() + gun.getDamage());

            MainCharacter.mainCharacter.setGun(gun) ;

            System.out.println("New weapon's ID : " + gun.getId());

            Queries query = new Queries () ;
            
            query.setDamage(MainCharacter.mainCharacter,MainCharacter.mainCharacter.getDamage());
            
            query.setGunId(MainCharacter.mainCharacter);
            
            query.setMoney(MainCharacter.mainCharacter);
            
            query = null ;
            
            return;

        } else if (moneyControl(gun) && MainCharacter.mainCharacter.getGun() == null) {

            MainCharacter.mainCharacter.setMoney(MainCharacter.mainCharacter.getMoney() - gun.getMoney());

            MainCharacter.mainCharacter.setDamage(MainCharacter.mainCharacter.getDamage() + gun.getDamage());

            MainCharacter.mainCharacter.setGun(gun);
            
            Queries query = new Queries () ;
            
            query.setDamage(MainCharacter.mainCharacter,MainCharacter.mainCharacter.getDamage());
            
            query.setGunId(MainCharacter.mainCharacter);
            
            query.setMoney(MainCharacter.mainCharacter);
            
            query = null ;

            System.out.println("New weapon's ID : " + gun.getId());

            return;

        }

        System.out.println("Your money is not sufficent");

        gun = null; // for gc

    }

    public void buyArmor(Armor armor) {
        
        if (MainCharacter.mainCharacter == null) {
            
            System.out.println("Once ana karakter seciniz.");
            return ;
            
        }

        if (moneyControl(armor) && MainCharacter.mainCharacter.getArmor() == null) {

            MainCharacter.mainCharacter.setArmor(armor);

            MainCharacter.mainCharacter.setDefence(MainCharacter.mainCharacter.getDefence() + armor.getDefence());

            MainCharacter.mainCharacter.setMoney(MainCharacter.mainCharacter.getMoney() - armor.getMoney());
            
            Queries query = new Queries () ;
            
            query.setDefence(MainCharacter.mainCharacter,MainCharacter.mainCharacter.getDamage());
            
            query.setArmorId(MainCharacter.mainCharacter);
            
            query.setMoney(MainCharacter.mainCharacter);
            
            query = null ;

            System.out.println("New armor ID : " + MainCharacter.mainCharacter.getArmor().getId());

            return;

        }

        if (moneyControl(armor) && MainCharacter.mainCharacter.getArmor() != null) {

            MainCharacter.mainCharacter.setDefenceToDefaultDefence();

            System.out.println("Old armor (ID : " + MainCharacter.mainCharacter.getArmor().getId() + ") removed.");

            MainCharacter.mainCharacter.setArmor(null); // for gc

            MainCharacter.mainCharacter.setArmor(null);

            MainCharacter.mainCharacter.setDefence(MainCharacter.mainCharacter.getDefence() + armor.getDefence());

            MainCharacter.mainCharacter.setMoney(MainCharacter.mainCharacter.getMoney() - armor.getMoney());
            
            Queries query = new Queries () ;
            
            query.setDefence(MainCharacter.mainCharacter,MainCharacter.mainCharacter.getDamage());
            
            query.setArmorId(MainCharacter.mainCharacter);
            
            query.setMoney(MainCharacter.mainCharacter);
            
            query = null ;

            System.out.println("New armor ID : " + MainCharacter.mainCharacter.getArmor().getId());

            return;

        }

        System.out.println("Your money is not sufficent");

        armor = null;

    }

    private boolean moneyControl(Equipment equipment) {

        if (equipment.getMoney() > MainCharacter.mainCharacter.getMoney()) {
            return false;
        }

        return true;

    }

}
