/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions;

import characters.Characters;
import characters.EnemyCharacter;
import characters.MainCharacter;
import java.util.logging.Level;
import java.util.logging.Logger;
import places.*;
import queries.Queries ;

/**
 *
 * @author kaan
 */
public class War {

    public <T extends Place> boolean war(Class<T> type) throws NullPointerException {

        if (MainCharacter.mainCharacter == null) {

            NullPointerException ex = new NullPointerException();

            System.out.println("Main Character is empty.");

            throw ex;

        }

        try {
            T place = type.newInstance();

            place.createEnemy();

        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(War.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("You have accrossed " + EnemyCharacter.enemies.size() + " " + EnemyCharacter.enemies.get(0).getName() + ".\nPrepare to battle !");

        return battle();

    }

    private boolean battle() {

        PlayerActions actions = new PlayerActions();

        int enemySize = EnemyCharacter.enemies.size();

        float gold = EnemyCharacter.enemies.get(0).getMoney();
        
        int xp = EnemyCharacter.enemies.get(0).getXp() ; 

        while (actions.healthControlOfMainCharacter() && !(EnemyCharacter.enemies.empty())) {
            
            
            actions.healthDownEnemy();
            
            if (!EnemyCharacter.enemies.empty()) actions.healthDownCharacter();
            

        }

        if (!actions.healthControlOfMainCharacter()) {

            System.out.println(Characters.characters.size());
            Characters.characters.remove(MainCharacter.mainCharacter);
            System.out.println(Characters.characters.size());
            EnemyCharacter.enemies.clear();
            MainCharacter.mainCharacter = null;
            return false;

        }

        System.out.println("Congrulations! You have killed all of the enemies.\nYou've earned " + (float) (gold * enemySize) + " gold.");
        
        MainCharacter.mainCharacter.getLevel().expUp(xp * enemySize);
        
        Queries query = new Queries () ;

        query.setHp(MainCharacter.mainCharacter);
        
        query.setMoney(MainCharacter.mainCharacter);
        
        return true;

    }

}
