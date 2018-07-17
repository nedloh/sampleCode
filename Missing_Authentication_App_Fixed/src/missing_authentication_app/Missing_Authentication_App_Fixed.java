
package missing_authentication_app;

import java.util.Scanner;

/**
 *
 * @author Tiffany Holden
 */
public class Missing_Authentication_App_Fixed {
static Scanner scannerIn = new Scanner(System.in);
    public static String[][] authenticatedUsers = {
            {"tHolden", "jHolden", "jParker", "kKurth","dFlowers"},
            {"password1", "password2", "password3", "password4","password5"}
            };
    public static boolean isUserAuthentic = false;
    public static void main(String[] args) {
            authenticateUser();
            
            if(isUserAuthentic){
                System.out.println("Enter new employee first name: ");
                String fname = scannerIn.next();
                System.out.println("Enter new employee last name: ");
                String lname = scannerIn.next();
                String name = fname + " " + lname;
                System.out.println("Monthly Salary Amount: ");
                String monthlySalary = scannerIn.next();
                int mSalary = Integer.parseInt(monthlySalary);
                Employee newEmployee = new Employee(name,mSalary);
                System.out.println("New Employee Created. Name: " + name + ", Monthly salary: $" + mSalary );
                
            }
            else
                System.out.println("Access Denied. Authentication failed.");
        
        
    }
    public static boolean authenticateUser(){
             
            System.out.println("Enter username to proceed: ");
            String username = scannerIn.next();
            System.out.println("Enter password: ");
            String password = scannerIn.next();
            int i;
            for(i=0; i<=4; i++){
                    if(username.equals(authenticatedUsers[0][i])){
                        if(password.equals(authenticatedUsers[1][i])) 
                            
                isUserAuthentic = true;
                }}
            
               return isUserAuthentic;
            }
            
   
}

  class Employee{
      private final String NAME;
      private final int MONTHLYSALARY;
      
      public Employee(String name, int monthlySalary){
          this.NAME = name;
          this.MONTHLYSALARY = monthlySalary;
      }
      
      public int annualSalary() {
          int annualSalary = MONTHLYSALARY * 12;
          return annualSalary;
      }
      
      
 }