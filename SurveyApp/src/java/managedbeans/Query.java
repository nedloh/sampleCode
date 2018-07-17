/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Owner
 */

@Named(value="Query")
@RequestScoped
public class Query {
    
    @Inject
    private Database database;
    private String id;
    private Survey survey;
    private List<String> surveyList;
    private String errorMsg;
    
    
    public Query(){
        id="";
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id){
        this.id=id;
    }
    
    public Survey getSurvey(){
        return survey;
    }
    
    public List<String> getSurveyList(){
        return surveyList;
    }
    
    public String getErrorMsg(){
        return errorMsg;
    }
    public String surveyListAction() {
    try {
      survey = database.getSurvey(id);
      surveyList = database.getSurveyList();
      return "surveys";
    } catch (Exception ex) {
      errorMsg = ex.getMessage();
      return "error";
    }
  }
}
