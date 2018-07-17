/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Owner
 */
public class DB_connection {
    
    public static void main(String[] args){
        DB_connection obj_DB_connection = new DB_connection();
        System.out.println(obj_DB_connection.get_connection());
    }
    public Connection get_connection(){
        Connection connection = null;
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/SurveyData","app","app");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
}
