/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import java.util.InputMismatchException;
import characters.Archer;
import characters.Warrior;
import characters.Wizard;
import characters.MainCharacter;
import exception.UniqueNameException;
import characters.Characters;
import actions.War;
import java.util.logging.Level;
import java.util.logging.Logger;
import places.Place;

/**
 *
 * @author kaan
 */
public class Menu {

    public <T extends Characters> T createCharacter(T type, String name) throws InputMismatchException, NullPointerException {

        boolean isMainCharacterNull = MainCharacter.mainCharacter == null;

        T character = null;

        if (type instanceof Archer) {

            try {

                character = (T) (Archer) Archer.createArcher(name);

            } catch (UniqueNameException ex) {

                ex.printStackTrace();

            }

        } else if (type instanceof Warrior) {

            try {

                character = (T) (Warrior) Warrior.createWarrior(name);

            } catch (UniqueNameException ex) {

                ex.printStackTrace();

            }

        } else if (type instanceof Wizard) {

            try {

                character = (T) (Wizard) Wizard.createWizard(name);

            } catch (UniqueNameException ex) {

                ex.printStackTrace();

            }

        }

        if (character == null) {

            NullPointerException ex = new NullPointerException();

            throw ex;

        }

        if (isMainCharacterNull) {

            MainCharacter.mainCharacter = character;

            System.out.println("Main Character : " + character.getName());

        }

        return character;

        /**
         *
         * T bir generic türdür ve bu kod, metodu çağıran yerde hangi karakter
         * türünün dönmesi gerektiğini belirtmek için kullanılmış olabilir.
         * Yani, bu metot çağrıldığında, T belirli bir karakter türünü temsil
         * edecek ve bu karakter türüne dönüştürülen result nesnesi geri
         * döndürülecektir. Örneğin, eğer bu metot Archer türünden bir karakteri
         * oluşturmak için çağrılırsa, T Archer olarak atanır ve sonuç olarak
         * return (Archer) result şeklinde bir dönüşüm yapılır. Aynı şekilde,
         * metot Warrior veya Wizard karakterlerini oluşturmak için çağrılırsa,
         * T sırasıyla Warrior veya Wizard olarak atanır ve sonuç nesnesi bu
         * karakter türüne dönüştürülür.
         *
         */
        /**
         *
         * result = (T) Archer.createArcher(name) ; kodu calistiginda
         * createArcher metodundan geriye donen nesnenin tipi T tipine mi
         * atanacak ?
         *
         *
         * Evet, result = (T) Archer.createArcher(name); kod satırı çalıştığında
         * createArcher metodundan dönen nesnenin tipi T tipine atanacak Bu
         * işlem, Generic tür (T) kullanımını temsil eder Ancak, bu işlem bir
         * tür dönüşümüdür ve bu nesnenin gerçekten T türüne dönüşüp
         * dönüşemeyeceği çalışma zamanında belirlenir Yani, createArcher
         * metodundan dönen nesne, T türüne dönüştürülmeye çalışılır ve bu tür
         * dönüşümü eğer mümkünse gerçekleşir Eğer createArcher metodunun dönüş
         * türü T ile uyumlu değilse veya çalışma zamanında hata olursa, bir
         * ClassCastException hatası alabilirsiniz Bu tür dönüşümleri
         * kullanırken dikkatli olmalısınız ve T türüne uygun olmayan bir
         * nesnenin atandığından emin olmalısınız Aksi takdirde çalışma
         * zamanında hatalar alabilirsiniz.
         *
         */
    }

    public <T extends Place> boolean war(Class<T> placeType) {

        War war = new War();

        return !war.war(placeType);

    }

}
