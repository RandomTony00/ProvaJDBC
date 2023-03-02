package provaJDBC.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import provaJDBC.model.Tutorial;

import java.util.List;

@Repository
public class TutorialRepo {

    @Autowired
    JdbcTemplate template;

    /*Getting a specific Tutorial by id from table*/
    public Tutorial getTutorialById(long id) {
        return template.queryForObject(
                "SELECT * FROM tutorial WHERE id=?",
                new BeanPropertyRowMapper<>(Tutorial.class), id
        );
    }

    /*Getting all Tutorials from table*/
    public List<Tutorial> getAllTutorials() {
        List<Tutorial> tutorials = template.query(
                "SELECT * FROM tutorial",
                (result, rowNum) ->
                        new Tutorial(
                                result.getLong("id"),
                                result.getString("title"),
                                result.getString("description"),
                                result.getBoolean("published")
                        )
        );
        return tutorials;
    }

    /*Getting a specific Tutorial by title from table*/
    public List<Tutorial> getTutorialByTitle(String title) {
        List<Tutorial> tutorials = template.query(
                "SELECT * FROM tutorial WHERE title=?",
                new Object[]{title},
                (result, rowNum) ->
                        new Tutorial(
                                result.getLong("id"),
                                result.getString("title"),
                                result.getString("description"),
                                result.getBoolean("published")
                        )
        );
        return tutorials;
    }

    /*Getting a specific Tutorial by published from table*/
    public List<Tutorial> getByPublished(boolean published) {
        List<Tutorial> tutorials = template.query(
                "SELECT * FROM tutorial WHERE published=?",
                new Object[]{"%" + published},
                (result, rowNum) ->
                        new Tutorial(
                                result.getLong("id"),
                                result.getString("title"),
                                result.getString("description"),
                                result.getBoolean("published")
                        )
        );
        return tutorials;
    }

    /*Adding a Tutorial into database table*/
    public Tutorial createTutorial(Tutorial tutorial) {
        String query = "INSERT INTO tutorial (title, description, published) VALUES(?,?,?)";
       // if(template.update(query,tutorial.getId(), tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished())==1){
            template.update(query,  tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished());
            return tutorial;
       // }
       /* else{
            return null;
        }*/
    }

    /*delete a Tutorial by id from database*/
    public Tutorial updateTutorial(Tutorial tutorial) {
        String query = "UPDATE tutorial SET title=?, description=?, published=? WHERE id=?";
       // if(template.update(query, tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished())==1){
            template.update(query, tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished(), tutorial.getId());
            return tutorial;
        /*}
        //else{
           // return null;
        }*/
    }

    /*delete a Tutorial by id from database*/
    public int deleteTutorial(long id) {
        String query = "DELETE FROM tutorial WHERE ID =?";
        return template.update(query, id);
    }

    /*delete all Tutorials from database*/
    public int deleteAllTutorials() {
        String query = "DELETE FROM tutorial";
        return template.update(query);
    }
}
