package org.MiniSurveyMonkey.Controllers;

import jakarta.servlet.http.HttpSession;
import org.MiniSurveyMonkey.Forms.Form;
import org.MiniSurveyMonkey.Repositories.FieldRepo;
import org.MiniSurveyMonkey.Repositories.FormRepo;
import org.MiniSurveyMonkey.Repositories.ResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
    public String createForm(Model m, HttpSession session){

        m.addAttribute("user", session.getAttribute("user"));

        return "baseForm";
    }

    @GetMapping("/submission-complete")
    public String SubmitFormResponse(){
        return "FormComplete";
    }

    @GetMapping("/form/{id}")
    public String getOneForm(@PathVariable(value = "id") String formId, Model m){
        m.addAttribute("formId", formId);

        Form form = formRepo.findById(formId).orElseThrow(() ->
                new ResourceNotFoundException("Could not find Form with that id"));
        m.addAttribute("formTitle", form.getFormName());

        if (form.isClosed()) {
            return "viewVisualizations";
        }

        return "viewForm";
    }
    @GetMapping("/forms")
    public String getForms(Model m){

        return "viewAllForms";
    }

    @GetMapping("/homePage/{name}")
    public String getHomePage(@ModelAttribute("user") @PathVariable(value = "name") String user, Model model, HttpSession session)
    {
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        return "homePage";
    }
}
