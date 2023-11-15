/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package places;

import characters.EnemyCharacter;

/**
 *
 * @author kaan
 */
public class GraveYard extends Place {

    @Override
    public void createEnemy() {

        super.enemyNumber = super.creator.nextInt(1, 3);

        if (!EnemyCharacter.enemies.empty()) {

            while (!EnemyCharacter.enemies.empty()) {

                EnemyCharacter.enemies.pop();

            }

        }

        for (int i = 0; i < super.enemyNumber; i++) {

            EnemyCharacter.createGhost();

        }

    }

}
