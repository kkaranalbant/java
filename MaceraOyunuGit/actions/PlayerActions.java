/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions;

import characters.MainCharacter;
import characters.EnemyCharacter;

/**
 *
 * @author kaan
 */
public class PlayerActions {

    PlayerActions() {
    }

    ;
    
    
    private boolean attackControlOfMainCharacter() {

        if (MainCharacter.mainCharacter.getDamage() - EnemyCharacter.enemies.peek().getDefence() <= 0) {
            return false;
        }

        return true;

    }

    private boolean attackControlOfEnemy() {

        if (EnemyCharacter.enemies.peek().getDamage() > MainCharacter.mainCharacter.getDefence()) {
            return true;
        }

        return false;

    }

    void healthDownEnemy() {

        float value = EnemyCharacter.enemies.peek().getHealth() - MainCharacter.mainCharacter.getDamage();

        EnemyCharacter.enemies.peek().setHealth(value);

        if (!healthControlOfEnemy()) {

            MainCharacter.mainCharacter.setMoney(MainCharacter.mainCharacter.getMoney() + EnemyCharacter.enemies.peek().getMoney());

            EnemyCharacter.enemies.pop();

        }

    }

    void healthDownCharacter() {

        float value = MainCharacter.mainCharacter.getHp() - EnemyCharacter.enemies.peek().getDamage();

        MainCharacter.mainCharacter.setHp(value);

    }

    boolean healthControlOfMainCharacter() {

        boolean isAlive = MainCharacter.mainCharacter.getHp() > 0;

        if (isAlive) {
            return true;
        }

        return false;

    }

    private boolean healthControlOfEnemy() {

        boolean isEnemyAlive = EnemyCharacter.enemies.peek().getHealth() > 0 ;

        if (isEnemyAlive) {
            return true;
        }

        return false;

    }

}
