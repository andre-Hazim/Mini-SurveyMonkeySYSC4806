package org.MiniSurveyMonkey.Controllers;

import org.MiniSurveyMonkey.Fields.Field;
import org.MiniSurveyMonkey.Forms.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.MiniSurveyMonkey.Repositories.FormRepo;
import org.MiniSurveyMonkey.Repositories.FieldRepo;
import org.MiniSurveyMonkey.Repositories.ResponseRepo;
import org.MiniSurveyMonkey.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private FormRepo formRepo;
    
    @Autowired
    private FieldRepo fieldRepo;

    @Autowired
    private ResponseRepo responseRepo;

    public RestController(FormRepo formRepo, FieldRepo fieldRepo, ResponseRepo responseRepo) {
        this.formRepo = formRepo;
        this.fieldRepo = fieldRepo;
        this.responseRepo = responseRepo;

    }

    /**
     * Get Mapping to retrieve a form by Id
     * @param id -  the id of the form
     * @return - JSON representation of the object
     */
    @GetMapping(value = "/form", produces = MediaType.APPLICATION_JSON_VALUE)
    public Form viewForm(@RequestParam String id) {

        //trying to find the form in the repo
        Form form = formRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Could not find Form with that id"));

        return form;
    }


    @PutMapping("/editForm")
    public Form editForm(@RequestParam String formId, @RequestParam ArrayList<Field> fields, Model m){
        ArrayList<Field> fieldInDb = (ArrayList<Field>) fieldRepo.findByFormId(formId);
        ArrayList<Field> toBeRemoved = new ArrayList<>(fieldInDb);
        toBeRemoved.removeAll(fields);
        ArrayList<Field> toBeAdded = new ArrayList<>(fields);
        toBeAdded.removeAll(fieldInDb);
        fieldRepo.deleteAll(toBeRemoved);
        fieldRepo.saveAll(toBeAdded);
        Form f = (Form) m.getAttribute("formId");
        assert f != null;
        f.setFields(fields);
        m.addAttribute("formId", f);
        return f;
    }
}
