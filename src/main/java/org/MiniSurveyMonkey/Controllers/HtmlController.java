package org.MiniSurveyMonkey.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/seeBaseForm")
    public String getAddresses(Model m){
        return "baseForm";
    }

    @GetMapping("/textField")
    public String getTextField(Model m){
        return "textField";
    }
}
