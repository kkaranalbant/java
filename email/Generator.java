/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package emailapp.email;

import java.util.Random;

/**
 *
 * @author kaan
 */
public abstract class Generator {
    
    private int minPassLength ;
    
    private static Random random = new Random () ;
    
    private static char [] specialCharacterArray = {'!','{','.','}','?','-','_'};
    
    Generator (int minPassLength) {
        this.minPassLength = minPassLength ;
    }

    public abstract Email generateEmail(String name, String lastName, String company);

    String generatePass() {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < minPassLength) {
            builder.append(createRandomLowerCaseLetter());
            builder.append(createRandomUpperCaseLetter());
            builder.append(createSpecialCharacter());
            builder.append(createRandomDigit());
        }
        return builder.toString();
    }
    
    private char createRandomUpperCaseLetter () {
        return (char) random.nextInt(65,91);
    }
    
    private char createRandomLowerCaseLetter () {
        return (char) random.nextInt(97,123) ;
    }
    
    private char createRandomDigit () {
        return (char) random.nextInt(48,58);
    }
    
    private char createSpecialCharacter () {
        int randomSpecialCharacterIndex = random.nextInt(0,specialCharacterArray.length) ;
        return specialCharacterArray[randomSpecialCharacterIndex];
    }
    
    int getMinPassLength () {
        return minPassLength ;
    }
}
