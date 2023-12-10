/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emailapp.email;

/**
 *
 * @author kaan
 */
public abstract class Email {

    private String name;
    private String lastName;
    private String department;
    private String company;
    private String email;
    private String password;
    private String alternateEmail;
    private int minPassLength;
    private final int MAX_PASS_LENGTH = 18;
    private int mailBoxCapacity;

    Email(String name, String lastName, String department, String company, String password, int minPassLength, int mailBoxCapacity) {
        this.name = name;
        this.lastName = lastName;
        this.department = department;
        this.company = company;
        this.email = name + "." + lastName + "@" + department + "." + company + ".com";
        this.password = password;
        this.minPassLength = minPassLength;
        this.mailBoxCapacity = mailBoxCapacity;
        displayInfoWithPassword();
    }

    public void setPassword(String pass) {
        if (minPassLength > pass.length()) {
            System.out.println("Bigger than characters :" + (minPassLength - 1));
        } else if (pass.length() > MAX_PASS_LENGTH) {
            System.out.println("Less than characters : " + (MAX_PASS_LENGTH + 1));
        } else if (!(containsDigit(pass) && containsLowerCaseLetter(pass) && containsUpperCaseLetter(pass)) && containsSpecialCharacter(pass)) {
            System.out.println("Password must contain at least one upper case character ,"
                    + "one lower case character , one digit and one special character.");
        } else {
            password = pass;
            displayInfoWithPassword();
        }
    }

    public void setMailBoxCapacity(int newCapacity) {
        if (newCapacity < 10) {
            System.out.println("At least 10 ! ");
        } else {
            mailBoxCapacity = newCapacity;
            System.out.println("Successful");
        }
    }
    
    public void setAlternateEmail (String alternateEmail) {
        this.alternateEmail = alternateEmail ;
    }

    private boolean containsDigit(String pass) {
        for (char c : pass.toCharArray()) {
            if (c >= 48 && c <= 57) {
                return true;
            }
        }
        return false;
    }

    private boolean containsUpperCaseLetter(String pass) {
        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsLowerCaseLetter(String pass) {
        for (char c : pass.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsSpecialCharacter(String pass) {
        for (char c : pass.toCharArray()) {
            if (!(Character.isLetterOrDigit(c)) && Character.isDefined(c)) {
                return true;
            }
        }
        return false;
    }

    private void displayInfoWithPassword() {
        System.out.println(email);
        System.out.println("Password :" + password);
        System.out.println("Mail Box Capacity : "+mailBoxCapacity);
        if (alternateEmail != null) System.out.println("Alternate e-mail : "+alternateEmail);
    }
    
    public void displayInfo () {
        System.out.println(email);
        System.out.println("Mail Box Capacity : "+mailBoxCapacity);
        if (alternateEmail != null) System.out.println("Alternate e-mail : "+alternateEmail);
    }
}
