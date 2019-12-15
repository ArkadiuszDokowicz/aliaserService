package Application.Controllers.FrontEnd;

import Application.Algorithm.MulitThreadAliaserAlgorithm;
import Application.Services.Aliaser.AliaserImpl;
import Application.api.databaseAPi.DataBaseApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {

    @Autowired
    AliaserImpl aliaser;
    @Autowired
    DataBaseApiImpl dataBaseApi;

    @GetMapping("getAdminPage")
    public String getAdminPage(){
        return "AdminTerminal";
    }
    @GetMapping("getExpertPage")
    public String getExpertPage(){
        return "ExpertTerminal";
    }
    @GetMapping("getAddRecipePage")
    public String getAddPage(Model model){



        return "NewRecipe";}
    @GetMapping("getClassifyPage")
    public String getClassifyPage(Model model){
       // model.addAttribute("text",value);
        //model.addAttribute("text",value);
       // model.addAttribute("text",value);
       // model.addAttribute("text",value);




        return "ClassifyPage";
    }
    @GetMapping("StartWorking")
    public String getAlgorithmPage() throws InterruptedException {
        MulitThreadAliaserAlgorithm algorithm = new MulitThreadAliaserAlgorithm(aliaser);
        algorithm.startThreads();
        return "AlgorithmPage";
    }

}
