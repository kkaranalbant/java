/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package characters;

import java.sql.ResultSet;

import java.sql.SQLException;

import menu.Menu;

/**
 *
 * @author kaan
 */
public class MainCharacter {

    public static Characters mainCharacter = null;

    public boolean setMainCharacter(String name) {

        for (Characters character : Characters.characters) {

            if (character.getName().equals(name)) {

                mainCharacter = character;

                System.out.println("Main Character : " + character.getName());

                return true;

            }

        }

        return false;

    }

    public boolean setMainCharacter(int id) {

        for (Characters character : Characters.characters) {

            if (character.getId() == id) {

                mainCharacter = character;

                System.out.println("Ana Karakter : " + character.getName());

                return true;

            }

        }

        return false;

    }

    public boolean setMainCharacterFromDb(ResultSet result) {

        boolean value = false ;
        
        try {

            if (result.next()) {

                String type = result.getString("charType");

                if (type.equals("Archer")) {
                    
                    Archer archer = new Archer () ;

                    MainCharacter.mainCharacter = Characters.createCharacterFromDb(archer , result);

                }
                
                else if (type.equals("Warrior")) {
                    
                    Warrior warrior = new Warrior () ;
                    
                    MainCharacter.mainCharacter = Characters.createCharacterFromDb(warrior, result) ;
                    
                }
                
                else {
                    
                    Wizard wizard = new Wizard () ;
                    
                    MainCharacter.mainCharacter = Characters.createCharacterFromDb(wizard, result) ;
                    
                }
                
                System.out.println("Main Character : "+mainCharacter.getName());
                
                value =  true ;

            } else {

                value = false;

            }

        } catch (SQLException ex) {

            ex.printStackTrace(); 
            
            value = false ;
            
        }
        
        return value ;

    }

}
