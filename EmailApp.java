/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package emailapp;

import emailapp.email.AccountingDepartmentEmailGenerator;
import emailapp.email.DevelopmentDepartmentEmailGenerator;
import emailapp.email.Email;
import emailapp.email.Generator;
import emailapp.email.SalesDepartmentEmailGenerator;

/**
 *
 * @author kaan
 */
public class EmailApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Generator generator = new SalesDepartmentEmailGenerator();
        Email test = generator.generateEmail("asd", "asd", "asd");

        test.setPassword("asd");
        test.setPassword("asdasdasdasdasdasdadad");
        test.setPassword("AS1XXXqqq122");
        test.setPassword("asd!!qqQQQQq");
        test.setPassword("asd!!!!!!!!11");
        test.setPassword("ASDASDASD!!!2222");
        test.setPassword("aB!2cccqwe");

        test.setAlternateEmail("asdasd.com");
        test.setMailBoxCapacity(12);

        test.displayInfo();

    }

}
