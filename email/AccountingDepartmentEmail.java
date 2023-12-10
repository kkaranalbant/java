
package emailapp.email;
/**
*
*@author kaan
*
*/
public class AccountingDepartmentEmail extends Email{
    AccountingDepartmentEmail(String name , String lastName , String department , String company , String password , int minPassLength , int mailBoxCapacity) {
        super (name , lastName , department , company , password , minPassLength , mailBoxCapacity);
    }
}
