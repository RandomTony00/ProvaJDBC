package provaJDBC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provaJDBC.model.Tutorial;
import provaJDBC.service.TutorialService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    @Qualifier("MainTutorial")
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(){
        return tutorialService.getAllTutorials();
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id){
        return tutorialService.getTutorialById(id);
    }

    @GetMapping("/tutorials/title/{title}")
    public ResponseEntity<List<Tutorial>> getTutorialByTitle(@PathVariable("title") String title) {
        return tutorialService.getTutorialByTitle(title);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialService.createTutorial(tutorial);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        return tutorialService.updateTutorial(id, tutorial);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        return tutorialService.deleteTutorial(id);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        return tutorialService.deleteAllTutorials();
    }

    @GetMapping("/tutorials/published/{published}")
    public ResponseEntity<List<Tutorial>> findByPublished(@PathVariable("published") boolean published) {
        return tutorialService.findByPublished(published);
    }
}