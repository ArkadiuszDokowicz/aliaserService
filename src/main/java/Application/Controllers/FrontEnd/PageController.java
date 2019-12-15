package Application.Controllers.FrontEnd;

import Application.Algorithm.*;
import Application.Model.Alias;
import Application.Model.Recipe;
import Application.Model.TestCase;
import Application.Services.Aliaser.AliaserImpl;
import Application.api.databaseAPi.DataBaseApiImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
                            @RequestParam(name="vege" ,required = false ,defaultValue = "false")Boolean vege,
                            @RequestParam(name="recipe")String description){
        dataBaseApi.addRecipe(name,description,vege);
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

        MulitThreadAliaserAlgorithm algorithm = new MulitThreadAliaserAlgorithm(aliaser,4);
        long start = System.nanoTime();
        algorithm.startAliaserThreads();
        long elapsedTime = System.nanoTime() - start;
        System.out.println(elapsedTime);
        ArrayList<Alias> aliases=aliaser.getAliasesAsArrayList();
        dataBaseApi.sendAliases(aliases);
        dataBaseApi.sendRecipes(RecipesBuffer.getInstance().getAllRecipesHashed());
        /*
        MulitThreadAliaserAlgorithm algorithm2= new MulitThreadAliaserAlgorithm(aliaser,4);
         start = System.nanoTime();
        algorithm2.startAliaserThreads();
        elapsedTime = System.nanoTime() - start;
        System.out.println(elapsedTime);
        aliases=aliaser.getAliasesAsArrayList();
        dataBaseApi.sendAliases(aliases);
        dataBaseApi.sendRecipes(RecipesBuffer.getInstance().getAllRecipesHashed());

        /*
        TestCaseProducer testCaseProducer = new TestCaseProducer(dataBaseApi.getDataBaseTableSize("recipe"));
        //System.out.println(testCaseProducer.getTestCases().size());
        int singleListSize =Math.round(testCaseProducer.getTestCases().size()/4)+1;
        List<List<TestCase>> testCases= Lists.partition(testCaseProducer.getTestCases(),singleListSize);
        TestCaseThread testCaseThread = new TestCaseThread(aliaser,testCaseProducer.getTestCases());
        testCaseThread.start();
        System.out.println(TestCasesBuffer.getInstance().getTestCases().size());
        */
        return "AlgorithmPage";

    }

}
