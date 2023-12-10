
package emailapp.email;

/**
 *
 * @author kaan
 */
public class DevelopmentDepartmentEmailGenerator extends Generator{

    public DevelopmentDepartmentEmailGenerator() {
        super(12);
    }

    
    
    @Override
    public Email generateEmail(String name, String lastName, String company) {
        String pass = super.generatePass() ;
        int minPassLength = super.getMinPassLength();
        Email email = new DevelopmentDepartmentEmail (name , lastName , "development",company , pass , minPassLength,10000);
        return email;
    }
    
}
