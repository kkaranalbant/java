
package emailapp.email;

/**
 *
 * @author kaan
 */
public class AccountingDepartmentEmailGenerator extends Generator {

    public AccountingDepartmentEmailGenerator() {
        super(16);
    }

    @Override
    public Email generateEmail(String name, String lastName, String company) {
        String pass = super.generatePass() ;
        int minPassLength = super.getMinPassLength();
        Email email = new AccountingDepartmentEmail(name, lastName, "accounting", company , pass , minPassLength , 10000);
        return email;
    }

}
