package Application.Controllers.FrontEnd;

import Application.Algorithm.*;
import Application.Model.Alias;
import Application.Model.Recipe;
import Application.Model.TestCase;
import Application.Services.Aliaser.AliaserImpl;
import Application.Services.TestCaseService;
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
    TestCaseService testCaseService;
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
            //TODO uncomment
        TestCase testCase = dataBaseApi.getTestCase();
            testCaseService.setTestCase(testCase);
            if(testCase!=null){

        }
        model.addAttribute("name1",testCase.getLeftId());
        model.addAttribute("description1",testCase.getLeftDescription());
        model.addAttribute("name2",testCase.getRightId());
        model.addAttribute("description2",testCase.getRightDescription());

        return "ClassifyPage";
    }

    @PostMapping("expertAnswerLeft")
        public String sendPositiveAnswerForLeft(){
        TestCase testCase = testCaseService.getTestCase();
        testCase.setStatus(2);
        dataBaseApi.sendTestCase(testCase);
        dataBaseApi.deleteAlias(testCase.getLeftAlias());
        dataBaseApi.updateRecipe(testCase.getRightId(),testCase.getLeftAlias());
        return "ExpertTerminal";
        }
    @PostMapping("expertAnswerRight")
    public String sendPositiveAnswerForRight(){
       // System.out.println(testCaseService.getTestCase().getRightId());
        TestCase testCase = testCaseService.getTestCase();
        testCase.setStatus(2);
        dataBaseApi.sendTestCase(testCase);
        dataBaseApi.deleteAlias(testCase.getLeftAlias());
        dataBaseApi.updateRecipe(testCase.getLeftId(),testCase.getRightAlias());
        return "ExpertTerminal";
    }


    @PostMapping("expertAnswerFalse")
    public String sendNegativeAnswer(){
        TestCase testCase = testCaseService.getTestCase();
        testCase.setStatus(2);
        dataBaseApi.sendTestCase(testCase);
        return "ExpertTerminal";
    }



        @GetMapping("StartWorking")
    public String getAlgorithmPage() throws InterruptedException {

        //aliasing
        MulitThreadAliaserAlgorithm algorithm = new MulitThreadAliaserAlgorithm(aliaser,4);
        long start = System.nanoTime();
            try {
                algorithm.startAliaserThreads();
                ArrayList<Alias> aliases=aliaser.getAliasesAsArrayList();

                dataBaseApi.sendAliases(aliases);
                dataBaseApi.sendRecipes(RecipesBuffer.getInstance().getAllRecipesHashed());
                generateTestCase();

            } catch (Exception e) {
                e.printStackTrace();
            }
        return "AlgorithmPage";

    }
    private void generateTestCase(){
        TestCaseProducer testCaseProducer = new TestCaseProducer(RecipesBuffer.getInstance().allRecipesNotHashed.size());
        TestCaseThread testCaseThread = new TestCaseThread(aliaser,testCaseProducer.getTestCases());
        testCaseThread.start();
        if(testCaseThread.getCheckedTestCases().size()>0){
            dataBaseApi.sendTestCases(testCaseThread.getCheckedTestCases());
        }

    }

}
