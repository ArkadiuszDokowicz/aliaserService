package Application.Controllers.FrontEnd;

import Application.Algorithm.MulitThreadAliaserAlgorithm;
import Application.Model.Recipe;
import Application.Services.Aliaser.AliaserImpl;
import Application.api.databaseAPi.DataBaseApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

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
    @PostMapping("addRecipeToBase")
    public String addRecord(@RequestParam(name="name")String name,
                            @RequestParam(name="vege")Boolean isVege,
                            @RequestParam(name="recipe")String description){
        dataBaseApi.addRecipe(name,description,isVege);
        return "ExpertTerminal";
    }
        @GetMapping("getClassifyPage")
    public String getClassifyPage(Model model){

        model.addAttribute("name1","name1 z uslugi");
        model.addAttribute("description1","description1 z usługi ");
        model.addAttribute("name2","name2 z uslugi");
        model.addAttribute("description2","description2 z usługi ");
        return "ClassifyPage";
    }
    @GetMapping("StartWorking")
    public String getAlgorithmPage() throws InterruptedException {
        MulitThreadAliaserAlgorithm algorithm = new MulitThreadAliaserAlgorithm(aliaser);
        algorithm.startThreads();
        return "AlgorithmPage";
    }

}
