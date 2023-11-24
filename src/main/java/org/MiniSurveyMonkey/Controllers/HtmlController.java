package org.MiniSurveyMonkey.Controllers;

import jakarta.websocket.server.PathParam;
import org.MiniSurveyMonkey.Repositories.FieldRepo;
import org.MiniSurveyMonkey.Repositories.FormRepo;
import org.MiniSurveyMonkey.Repositories.ResponseRepo;
import org.MiniSurveyMonkey.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HtmlController {

    @Autowired
    private FormRepo formRepo;

    @Autowired
    private FieldRepo fieldRepo;

    @Autowired
    private ResponseRepo responseRepo;

    @GetMapping("/seeBaseForm")
    public String getAddresses(Model m){
        return "baseForm";
    }

    @GetMapping("/form/{id}")
    public String getOneForm(@PathVariable(value = "id") String formId, Model m){
        m.addAttribute("formId", formId);
        return "viewForm";
    }
    @GetMapping("/forms")
    public String getForms(Model m){

        return "viewAllForms";
    }

    @GetMapping("/homePage/{name}")
    public String getHomePage(@PathVariable(value = "name") String user, Model model){
        model.addAttribute("user", user);
        return "homePage";
    }
}
