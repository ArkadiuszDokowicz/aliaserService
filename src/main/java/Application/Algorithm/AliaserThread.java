package Application.Algorithm;

import Application.Model.Recipe;
import Application.Model.TestCase;
import Application.Services.Aliaser.AliaserImpl;
import Application.api.databaseAPi.DataBaseApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class AliaserThread  {

    AliaserImpl aliaser;
    private DataBaseApiImpl dataBaseApi = new DataBaseApiImpl();
    private ArrayList<Recipe> recipes= new ArrayList<>();
    private int firstIndex,lastIndex;
    private ArrayList<TestCase> testCases;
    private ArrayList<TestCase> checkedTestCases = new ArrayList<>();

    AliaserThread(int first,int last,AliaserImpl aliaser ){
        this.firstIndex=first;
        this.lastIndex=last;
        this.aliaser=aliaser;
    }
    AliaserThread(AliaserImpl aliaser, ArrayList<TestCase> testCases) {
        this.testCases = testCases;
        this.aliaser=aliaser;
    }

    AliaserThread(List<TestCase> testCases) {
        this.testCases = (ArrayList<TestCase>) testCases;
    }


    public void start(){
        recipes=dataBaseApi.getRecipesForRange(firstIndex,lastIndex);
        RecipesBuffer.getInstance().addNotHashedRecipes(recipes);
        ArrayList<Recipe> aliasedRecipes= new ArrayList<>();
        for(Recipe r:recipes){
            String aliasedWords = aliaser.getAliasedMessage(r.getDescription());
            String aliasedMessage =aliaser.getOneAliasFromAliasedMessage(aliasedWords);
                    aliasedRecipes.add(new Recipe(r.getId(),r.getName(),aliasedMessage, r.isVege()));
        }
        RecipesBuffer.getInstance().addRecipes(aliasedRecipes);
       }

    public void start2() throws Exception {
        System.out.println("method2");
        for(TestCase testCase: testCases){
            Recipe left=RecipesBuffer.getInstance().getRecipeById(testCase.getLeftId());
            Recipe right=RecipesBuffer.getInstance().getRecipeById(testCase.getRightId());
            int leftWordAmount=0,rightWordAmount=0;
            leftWordAmount=getSplitWords(left.getDescription()).length;
            rightWordAmount=getSplitWords(right.getDescription()).length;
            if(leftWordAmount == rightWordAmount){
                if(aliaser.getSimilarWordsAmount(left.getDescription(),right.getDescription())==leftWordAmount){
                    checkedTestCases.add(new TestCase(left.getId(),left.getDescription(),"",right.getId(),right.getDescription(),""));

                }
            }
        }
        if(this.checkedTestCases.size()>0) {
            TestCasesBuffer.getInstance().addTestCases(checkedTestCases);
        }


    }


    private String[] getSplitWords(String s){
        String removedSpecialChars = removeSpecialCharacters(s);
        return removedSpecialChars.split(" ");
    }

    protected String removeSpecialCharacters(String s){
        return s.replaceAll("[-+.^:,()]","");

    }
}
