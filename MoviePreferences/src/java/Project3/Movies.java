
package Project3;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Tiffany Holden
 */

@Named
@ApplicationScoped
public class Movies {
    
    String name;
    String numberOfMovies;
    String genre;
    String[] genres;
   
    
    public Movies() {
    }
  
    public String[] getGenres(){
        return genres;
    } 
    public void setGenres(String[] genres){
        this.genres=genres;
    }
    public String getName() {
        return name;
    }

    public void setName(String user_name) {
        this.name = user_name;
    }
    
     public String getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(String numMovies) {
        this.numberOfMovies = numMovies;
    }
    
     public String getGenre() {
        return genre;
    }

    public void setGenre(String selectedGenre) {
        this.genre = selectedGenre;
        
    
    }

     
    
}
