/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package experience;

import characters.MainCharacter;
import java.util.InputMismatchException;
import java.util.Scanner;

import queries.Queries;

/**
 *
 * @author kaan
 */
public class Level {

    private int level;

    private int perk;

    public Level() {

        level = 0;
        perk = 0;

    }

    private boolean levelUpControl() {

        if (MainCharacter.mainCharacter.getExperience() >= MainCharacter.mainCharacter.getMaxExp()) {

            levelUp();

            return true;

        }

        return false;

    }

    private void levelUp() {

        int newExp = MainCharacter.mainCharacter.getExperience() - MainCharacter.mainCharacter.getMaxExp();

        MainCharacter.mainCharacter.setExperience(newExp);

        int newMaxExp = MainCharacter.mainCharacter.getMaxExp() * 13 / 10;

        MainCharacter.mainCharacter.setMaxExp(newMaxExp);

        MainCharacter.mainCharacter.getLevel().level++;

        MainCharacter.mainCharacter.getLevel().perk++;

        System.out.println("Your level increased.\nYour new level : " + level);

        usePerk();

    }

    public void expUp(int xp) {

        int newExperience = MainCharacter.mainCharacter.getExperience() + xp;

        MainCharacter.mainCharacter.setExperience(newExperience);

        Queries query = new Queries();

        if (levelUpControl()) {

            query.setLevel(MainCharacter.mainCharacter);
            query.setMaxExp(MainCharacter.mainCharacter);
            query.setExp(MainCharacter.mainCharacter, MainCharacter.mainCharacter.getExperience());
            query.setPerk(MainCharacter.mainCharacter);

        } else {

            query.setExp(MainCharacter.mainCharacter, newExperience);

        }

    }

    public int getLevel() {

        return level;

    }

    public void usePerk() {

        Queries query = new Queries();

        if (perk > 0) {

            String info = "1 - +4 hp\n2 - +2 damage\n3 - +1 defence\n4 - don't choose";

            int choice = 0;
            
            Scanner scanner = new Scanner (System.in) ;

            boolean loop = true;

            while (loop) {

                System.out.println(info);

                try {
                    
                    choice = scanner.nextInt() ;
                    
                }
                
                catch (InputMismatchException ex) {
                    
                    System.out.println("Lutfen gecerli bir deger giriniz.");
                    scanner.next() ;
                    continue ;
                    
                    
                }
                
                switch (choice) {

                    case 1: {
                        MainCharacter.mainCharacter.setDefaultHp(MainCharacter.mainCharacter.getDefaultHp() + 4);
                        loop = false;
                        perk--;
                        System.out.println("+4 hp added to :" + MainCharacter.mainCharacter.getName());
                        query.setHp(MainCharacter.mainCharacter);
                        query.setDefaultHp (MainCharacter.mainCharacter) ;
                        break;
                    }

                    case 2: {

                        MainCharacter.mainCharacter.setDamage(MainCharacter.mainCharacter.getDamage() + 2);
                        MainCharacter.mainCharacter.setDefaultDamage(MainCharacter.mainCharacter.getDamage());
                        loop = false;
                        perk--;
                        System.out.println("+2 damage added to :" + MainCharacter.mainCharacter.getName());
                        query.setDamage(MainCharacter.mainCharacter, MainCharacter.mainCharacter.getDamage());
                        query.setDefaultDamage(MainCharacter.mainCharacter) ;
                        break;

                    }

                    case 3: {

                        MainCharacter.mainCharacter.setDefence(MainCharacter.mainCharacter.getDefence() + 1);
                        MainCharacter.mainCharacter.setDefaultDefence(MainCharacter.mainCharacter.getDefence());
                        loop = false;
                        perk--;
                        System.out.println("+1 defence added to :" + MainCharacter.mainCharacter.getName());
                        query.setDefence(MainCharacter.mainCharacter,MainCharacter.mainCharacter.getDefence());
                        query.setDefaultDefence(MainCharacter.mainCharacter) ;
                        break;

                    }

                    case 4: {

                        loop = false;
                        System.out.println("Your perks : " + perk);
                        break;

                    }

                    default: {

                        System.out.println("Invalid Entry");
                        break;

                    }

                }

            }

        } else {

            System.out.println("Yetersiz perk.");

        }

        query.setPerk(MainCharacter.mainCharacter);

    }
    
    public void setLevel (int level) {
        
        this.level = level ;
        
    }

    public int getPerk() {

        return perk;

    }
    
    public void setPerk (int perk) {
        
        this.perk = perk ; 
        
    }

}
