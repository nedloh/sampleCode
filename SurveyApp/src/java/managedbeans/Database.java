/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;


/**
 *
 * @author Owner
 */


@Named(value= "database")
@ApplicationScoped
public class Database {
    
    @Resource(name="jdbc:derby://localhost:1527/SurveyData")
    private DataSource surveydataSource;
    
    private List<String> buildList(ResultSet resultSet, String coulumnName) throws SQLException{
        List<String> list = new ArrayList<String>();
        while (resultSet.next()){
            list.add(resultSet.getString(coulumnName));
        }
        return list;
    }
    
    private Survey buildSurvey(ResultSet resultSet) throws SQLException {
        resultSet.next();
        String id = Integer.toString(resultSet.getInt("ID"));
        String fname = resultSet.getString("FIRSTNAME");
        String lname = resultSet.getString("LASTNAME");
        String age = Integer.toString(resultSet.getInt("AGE"));
        String occupation = resultSet.getString("OCCUPATION");
        String children = resultSet.getString("CHILDREN");
        String married = resultSet.getString("MARRIED");
        String registeredAs = resultSet.getString("REGISTEREDAS");
        
        return new Survey(id, fname, lname, age, occupation, children, married, registeredAs);
    }
    
    public Database(){
        
    }
    
    public List<String> getSurveyList() throws SQLException, IOException{
        
        Connection surveyConnection = surveydataSource.getConnection();
        try{
            Statement statement = surveyConnection.createStatement();
            String query = "SELECT ID FROM SURVEYTABLE1";
            ResultSet resultSet = statement.executeQuery(query);
            return buildList(resultSet,"ID"); 
        }
        finally{
            surveyConnection.close();
        }
    }
    
    public Survey getSurvey(String id) throws SQLException, IOException{
        String query = "SELECT * FROM SURVEYTABLE1 WHERE ID=? ";
        Connection surveyConnection = surveydataSource.getConnection();
        try{
            PreparedStatement pstatement = surveyConnection.prepareStatement(query);
            pstatement.setString(1,id);
            ResultSet resultSet = pstatement.executeQuery();
            return buildSurvey(resultSet);
        }
        finally{
            surveyConnection.close();
        }
    }
}
