/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Owner
 */
@ManagedBean
@RequestScoped
@Named(value = "category")

public class Category {

    private String category_name;
    private String id;
    private String firstname;
    private String age;
    private String occupation;
    private String children;
    private String married;
    private String registeredAs;
    ArrayList<Category> searchlist=new ArrayList<Category>();
    
    
    public void setId(String id)
    {
        this.id=id;
    }    
    
    public String getId(){
        return id;
    }
    public void setSearchlist(ArrayList<Category> searchlist)
    {
        this.searchlist=searchlist;
    }    
    
    public ArrayList<Category> getSearchlist(){
        return searchlist;
    }
     public void setFirstname(String firstname)
    {
        this.firstname=firstname;
    }    
    
    public String getFirstname(){
        return firstname;
    }
     public void setAge(String age)
    {
        this.age=age;
    }    
    
    public String getAge(){
        return age;
    }
     public void setOccupation(String occupation)
    {
        this.occupation=occupation;
    }    
    
    public String getOccupation(){
        return occupation;
    }
     public void setChildren(String children)
    {
        this.children=children;
    }    
    
    public String getChildren(){
        return children;
    }
     public void setMarried(String married)
    {
        this.married=married;
    }    
    
    public String getMarried(){
        return married;
    }
     public void setRegisteredAs(String registeredAs)
    {
        this.registeredAs=registeredAs;
    }    
    
    public String getRegisteredAs(){
        return registeredAs;
    }
    public void setCategory_name(String category_name){
        this.category_name=category_name;
    }
     
    public String getCategory_name(){
        return category_name;
    }
    
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    
  public String edit_Category(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
     String field_id= params.get("action");
     try {
          DB_connection obj_DB_connection=new DB_connection();
          Connection connection=obj_DB_connection.get_connection();
          Statement st=connection.createStatement();
          ResultSet rs=st.executeQuery("SELECT * FROM SURVEYTABLE1 WHERE ID='"+field_id+"'");
          Category obj_Category=new Category();
          rs.next();
          obj_Category.setCategory_name(rs.getString("LASTNAME"));
          obj_Category.setId(rs.getString("ID"));
          obj_Category.setFirstname(rs.getString("FIRSTNAME"));
          obj_Category.setAge(rs.getString("AGE"));
          obj_Category.setOccupation(rs.getString("OCCUPATION"));
          obj_Category.setChildren(rs.getString("CHILDREN"));
          obj_Category.setMarried(rs.getString("MARRIED"));
          obj_Category.setRegisteredAs(rs.getString("REGISTEREDAS"));
          
          sessionMap.put("editcategory", obj_Category);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/edit.xhtml?faces-redirect=true";   
}

   
   public String delete_Category(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String field_id= params.get("action");
      try {
         DB_connection obj_DB_connection=new DB_connection();
         Connection connection=obj_DB_connection.get_connection();
       PreparedStatement ps=connection.prepareStatement("DELETE FROM SURVEYTABLE1 WHERE ID=?");
         ps.setString(1, field_id);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
}

   public String update_category(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String	update_id= params.get("update_id");
        try {
            DB_connection obj_DB_connection=new DB_connection();
            Connection connection=obj_DB_connection.get_connection();
      PreparedStatement ps=connection.prepareStatement("UPDATE SURVEYTABLE1 SET LASTNAME=?,FIRSTNAME=?,AGE=?,OCCUPATION=?,CHILDREN=?,MARRIED=?,REGISTEREDAS=? where ID=?");
            ps.setString(1, category_name);
            ps.setString(2,firstname);
            ps.setString(3,age);
            ps.setString(4,occupation);
            ps.setString(5,children);
            ps.setString(6,married);
            ps.setString(7,registeredAs);
            ps.setString(8, update_id);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
}
   public ArrayList<Category> search(){
       
       try{
           DB_connection obj_DB_connection=new DB_connection();
            Connection connection=obj_DB_connection.get_connection();
           PreparedStatement ps=connection.prepareStatement("SELECT * FROM SURVEYTABLE1 WHERE LASTNAME=? AND FIRSTNAME=?");
           ps.setString(1, category_name);
           ps.setString(2,firstname); 
           ResultSet rs=ps.executeQuery();
           
            while(rs.next()){
             Category obj_Category=new Category();
             
             obj_Category.setCategory_name(rs.getString("LASTNAME"));
             obj_Category.setId(rs.getString("ID"));
             obj_Category.setFirstname(rs.getString("FIRSTNAME"));
             obj_Category.setAge(rs.getString("AGE"));
             obj_Category.setOccupation(rs.getString("OCCUPATION"));
             obj_Category.setChildren(rs.getString("CHILDREN"));
             obj_Category.setMarried(rs.getString("MARRIED"));
             obj_Category.setRegisteredAs(rs.getString("REGISTEREDAS"));
             
             searchlist.add(obj_Category);
           }
       }
       catch(Exception e){
           System.out.println(e);
       }
       return searchlist;
   }

    
    public ArrayList<Category> getGet_all_category(){
         ArrayList<Category> list_of_categories=new ArrayList<Category>();
        try{
          
           Connection connection = null;
           DB_connection obj_DB_connection = new DB_connection();
           connection=obj_DB_connection.get_connection();
           Statement st = connection.createStatement();
           
           ResultSet rs = st.executeQuery("SELECT * FROM SURVEYTABLE1");
           while(rs.next()){
             Category obj_Category=new Category();
             
             obj_Category.setCategory_name(rs.getString("LASTNAME"));
             obj_Category.setId(rs.getString("ID"));
             obj_Category.setFirstname(rs.getString("FIRSTNAME"));
             obj_Category.setAge(rs.getString("AGE"));
             obj_Category.setOccupation(rs.getString("OCCUPATION"));
             obj_Category.setChildren(rs.getString("CHILDREN"));
             obj_Category.setMarried(rs.getString("MARRIED"));
             obj_Category.setRegisteredAs(rs.getString("REGISTEREDAS"));
             
             list_of_categories.add(obj_Category);
           }
        }
        catch(Exception e){
         System.out.println(e);
        }
        return list_of_categories;
    }
    
    public void add_id(){
        
        try{
          
           Connection connection = null;
           DB_connection obj_DB_connection = new DB_connection();
           connection=obj_DB_connection.get_connection();
           PreparedStatement ps = connection.prepareStatement("INSERT INTO SURVEYTABLE1(ID) VALUES('"+id+"') ");
           
           ps.executeUpdate();
          
           }
        
        catch(Exception e){
         System.out.println(e);
        }
       
    }
    
    
    public Category() {
    }
    
}
