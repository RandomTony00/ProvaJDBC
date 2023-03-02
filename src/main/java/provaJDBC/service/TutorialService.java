package provaJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import provaJDBC.model.Tutorial;
import provaJDBC.repository.TutorialRepo;
import java.util.ArrayList;
import java.util.List;


@Service("MainTutorial")
public class TutorialService {

    @Autowired
    TutorialRepo tutorialRepo;

    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        try {
            List<Tutorial> tutorials = new ArrayList<>(tutorialRepo.getAllTutorials());
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(tutorials, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Tutorial tutorialData = tutorialRepo.getTutorialById(id);

        if (tutorialData !=null) {
            return new ResponseEntity<>(tutorialData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Tutorial>> getTutorialByTitle(@PathVariable("title") String title) {
        List<Tutorial> tutorials = tutorialRepo.getTutorialByTitle(title);

        if (!tutorials.isEmpty()) {
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            Tutorial tutorialNew = tutorialRepo.createTutorial(tutorial);
            if (tutorialNew !=null) {
                return new ResponseEntity<>(tutorialNew, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Tutorial tutorialData = tutorialRepo.getTutorialById(id);

        if (tutorialData !=null) {
            tutorialData.setTitle(tutorial.getTitle());
            tutorialData.setDescription(tutorial.getDescription());
            tutorialData.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepo.updateTutorial(tutorialData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialRepo.deleteTutorial(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepo.deleteAllTutorials();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Tutorial>> findByPublished(boolean published) {
        try {
            List<Tutorial> tutorials = tutorialRepo.getByPublished(published);
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
