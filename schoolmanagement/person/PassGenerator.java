/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolmanagement.person;

import java.util.List;
import java.util.Random;

/**
 *
 * Bu sinifin bir tane nesnesinin olusmasi yeterli
 * 
 * Bu sinifin nesnesi PersonFactory sinifinin nesnesi ile iletisime gececek
 * 
 * @author kaan
 */

/*
    Singleton
*/

class PassGenerator {
    private static final int NORMAL_STUDENT_FIRST_ID_DIGIT = 1000;
    private static final int NORMAL_STUDENT_LAST_ID = 4000;
    private static final int WORKING_STUDENT_FIRST_ID = 4000;
    private static final int WORKING_STUDENT_LAST_ID = 6000;
    private static final int TEACHER_FIRST_ID = 6000;
    private static final int TEACHER_LAST_ID = 10000;
    private static final Random random = new Random();
    private static PassGenerator passGenerator = null ;
    
    private PassGenerator () {}
    
    static PassGenerator createPassGenerator () {
        if (passGenerator == null) {
            passGenerator = new PassGenerator () ;
        }
        return passGenerator ;
    }
    
    int createUniqueTeacherId() {
        int id = random.nextInt(TEACHER_FIRST_ID,TEACHER_LAST_ID);
        while (!isUniqueId(PersonManager.teacherList, id)) {
            id = random.nextInt(TEACHER_FIRST_ID,TEACHER_LAST_ID);
        }
        return id ;
    }

    int createUniqueWorkingStudentId() {
        int id = random.nextInt(WORKING_STUDENT_FIRST_ID,WORKING_STUDENT_LAST_ID);
        while (!isUniqueId(PersonManager.workingStudentList, id)) {
            id = random.nextInt(WORKING_STUDENT_FIRST_ID,WORKING_STUDENT_LAST_ID);
        }
        return id ;
    }

    int createUniqueStudentId() {
        int id = random.nextInt(NORMAL_STUDENT_FIRST_ID_DIGIT,NORMAL_STUDENT_LAST_ID);
        while (!isUniqueId(PersonManager.normalStudentList, id)) {
            id = random.nextInt(NORMAL_STUDENT_FIRST_ID_DIGIT,NORMAL_STUDENT_LAST_ID);
        }
        return id ;
    }

    /*
        3 tane tipi ayri ayri metotlarda kontrol etmek yerine generic
    */
    private <T extends Person> boolean isUniqueId(List <T> list , int id) {
        for (T current : list) {
            if (current.getUID() == id) return false ;
        }
        return true ;
    }
}
