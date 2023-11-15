/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package characters;

import exception.UniqueNameException;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import queries.Queries;

/**
 *
 * @author kaan
 */
public class Warrior extends Characters {

    private static Random random = new Random();

    private static Supplier<Integer> sp = () -> random.nextInt(2000, 3000);

    private Warrior(String name, int id) {

        super.setDefaultHp(25);
        super.setDamage(6);
        super.setId(id);
        super.setName(name);
        super.setHp(25);
        super.setResetDamage(6);
        super.setDefence(2);
        super.setMoney(8);
        super.setDefenceToDefaultDefence();

    }

    public Warrior() {
    }

    ;
    

    /**
     *
     * Bu metot statik factory metottur
     *
     * unique bir id olusturur
     *
     * Name kontrolu yapar ve eger name essiz degilse istisna firlatir
     *
     * name essiz ise constructor cagirir
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static Characters createWarrior(String name) throws UniqueNameException {

        int id = sp.get();

        boolean uniqueId = idControl(id) && idControlForDb(id) ;

        while (!uniqueId) {

            id = sp.get();

            uniqueId = idControl(id);

        }

        boolean uniqueName = nameControl(name) && nameControlForDb(name) ;

        if (uniqueName) {

            Warrior warrior = new Warrior(name, id);

            Characters.characters.add(warrior);

            System.out.println("Successful.");

            return warrior;

        } else {

            UniqueNameException ex = new UniqueNameException();

            throw ex;

        }

    }

    private static boolean idControl(int id) {

        for (int i = 0; i < Characters.characters.size(); i++) {

            if (Characters.characters.get(i).getId() == id) {
                return false;
            }

        }

        return true;

    }
    
    private static boolean idControlForDb(int id) {

        Queries query = new Queries();

        List<Integer> allId = query.getAllId();

        for (int i : allId) {

            if (id == i) {

                query = null;

                return false;

            }

        }

        query = null;

        return true;

    }

    private static boolean nameControl(String name) {

        for (int i = 0; i < Characters.characters.size(); i++) {

            if (Characters.characters.get(i).getName().equals(name)) {
                return false;
            }

        }

        return true;

    }
    
    private static boolean nameControlForDb(String name) {

        Queries query = new Queries();

        List<String> allNames = query.getAllNames();

        for (String tmp : allNames) {

            if (name.equals(tmp)) {

                query = null;

                return false;

            }

        }

        query = null;

        return true;

    }

    @Override
    public String toString() {

        return "Warrior";

    }

}
