
package emailapp.email;

/**
 *
 * @author kaan
 */
public class DevelopmentDepartmentEmail extends Email{

    DevelopmentDepartmentEmail(String name, String lastName, String department, String company , String password , int minPassLength , int mailBoxCapacity) {
        super(name, lastName, department, company , password , minPassLength , mailBoxCapacity);
    }
    
}
