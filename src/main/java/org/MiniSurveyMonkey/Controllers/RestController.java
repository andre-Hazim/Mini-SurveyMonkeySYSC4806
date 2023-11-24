package org.MiniSurveyMonkey.Controllers;

import org.MiniSurveyMonkey.Fields.Field;
import org.MiniSurveyMonkey.Forms.Form;
import org.MiniSurveyMonkey.Repositories.UserRepo;
import org.MiniSurveyMonkey.User;
import org.bson.types.ObjectId;
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

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private FormRepo formRepo;
    
    @Autowired
    private FieldRepo fieldRepo;

    @Autowired
    private ResponseRepo responseRepo;

    @Autowired
    private UserRepo userRepo;

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
    @GetMapping(value = "/getForm/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Form viewForm(@PathVariable String id) {

        //trying to find the form in the repo
        Form form = formRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Could not find Form with that id"));

        return form;
    }

    @PostMapping("/submitForm")
    public String submitForm(@RequestBody Form form){
        System.out.println("Received Form: " + form); //added this for testing purposed
        form.setId(ObjectId.get().toString());
        for (Field f : form.getFields()) {
            f.setFormId(form.getId());
        }

        fieldRepo.saveAll(form.getFields());
        formRepo.save(form);

        System.out.println("Form name: " + form.getFormName());
        System.out.println("Form Consists of fields: " + form.getFields()); // testing purposes
        return "{\"FormId\" : \""+form.getId()+"\"}";
    }

    @GetMapping("/getFieldRest")
    public List<Field> getField( Model model){
        List<Field> f1 = fieldRepo.findAll();
        if (f1.isEmpty()) return null;
        model.addAttribute("Fields", f1);
        return f1;
    }
    @GetMapping("/getFormsRest")
    public List<Form> getForms( Model model){
        List<Form> f1 = formRepo.findAll();
        if (f1.isEmpty()) return null;
        model.addAttribute("Forms", f1);
        return f1;
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

    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println("/Login Received this user: " + user);
        for (User i: userRepo.findAll()){
            if (i.getUsername().equals(user.getUsername())){
                System.out.println("User Found");
                return "{\"Username\" : \""+user.getUsername()+"\"}";
            }
        }
        userRepo.save(user);
        System.out.println("New User!");
        return "{\"Username\" : \""+user.getUsername()+"\"}";
    }

}
