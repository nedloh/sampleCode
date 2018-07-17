/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

/**
 *
 * @author Owner
 */
public class Survey {
    
    private String id;
    private String fname;
    private String lname;
    private String age;
    private String occupation;
    private String children;
    private String married;
    private String registeredAs;
    
    public Survey(String id, String fname, String lname, String age, String occupation, String children, String married, String registeredAs){
        this.id=id;
        this.fname=fname;
        this.lname=lname;
        this.age=age;
        this.occupation = occupation;
        this.children=children;
        this.married = married;
        this.registeredAs = registeredAs;
    }
    
    public String getID(){
        return id;
    }
    
     public String getFname(){
        return fname;
    }
     
    public String getLname(){
        return lname;
    }
    
    public String getAge(){
        return age;
    }
    
    public String getOccupation(){
        return occupation;
    }
    public String getChildren(){
        return children;
    }
   
    public String getMarried(){
        return married;
    }
    public String getRegisteredAs(){
        return registeredAs;
    }
}
