/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emailapp.email;

/**
 *
 * @author kaan
 */
public class SalesDepartmentEmailGenerator extends Generator {

    public SalesDepartmentEmailGenerator() {
        super(8);
    }

    @Override
    public Email generateEmail(String name, String lastName, String company) {
        String pass = super.generatePass() ;
        int minPassLength = super.getMinPassLength();
        Email email = new SalesDepartmentEmail(name, lastName, "sales", company , pass , minPassLength , 10000);
        return email;
    }

}
