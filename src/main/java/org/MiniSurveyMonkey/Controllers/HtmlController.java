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

    /**
     * Get Mapping to retrieve the baseForm page
     * @param m - the model
     * @param session - the current session
     * @return - HTML baseForm
     */
    @GetMapping("/seeBaseForm")
    public String createForm(Model m, HttpSession session){

        m.addAttribute("user", session.getAttribute("user"));

        return "baseForm";
    }

    /**
     * Get Mapping to retrieve the FormComplete page when a user responds to a form
     * @return - HTML FormComplete
     */
    @GetMapping("/submission-complete")
    public String SubmitFormResponse(){
        return "FormComplete";
    }

    /**
     * Get Mapping to retrieve a specific form given an id
     * @param formId - the id of the form
     * @param m - the model
     * @return - the form analytics (viewGraphs) if the form is closed, otherwise the regular form (viewForm)
     */
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

    /**
     * Get Mapping to see the all forms page
     * @param m - the model
     * @return - HTML viewAllForms
     */
    @GetMapping("/forms")
    public String getForms(Model m){

        return "viewAllForms";
    }

    /**
     * Get Mapping to see the home page
     * @param user - the current user
     * @param model - the model
     * @param session - the current session
     * @return - HTML homePage
     */
    @GetMapping("/homePage/{name}")
    public String getHomePage(@ModelAttribute("user") @PathVariable(value = "name") String user, Model model, HttpSession session)
    {
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        return "homePage";
    }

    /**
     * Method to fetch the form
     * @param m
     * @para formId - to fetch the id of the form
     * @return the edit Form main page
     */
    @GetMapping("/editForm/{formId}")
    public String editPage(@PathVariable(value = "formId") String formId, Model m){
        m.addAttribute("formId", formId);

        Form form = formRepo.findById(formId).orElseThrow(() ->
                new ResourceNotFoundException("Could not find Form with that id"));
        m.addAttribute("formTitle", form.getFormName());
        m.addAttribute("formAuthor", form.getAuthor());
        return "editForm";
    }
}
