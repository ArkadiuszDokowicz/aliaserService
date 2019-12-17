package Application.Algorithm;

import Application.Model.Recipe;
import Application.Model.TestCase;
import Application.Services.Aliaser.AliaserImpl;

import java.util.ArrayList;

public class TestCaseThread   {

    private final double TOLERANCE = 0.10;
    private AliaserImpl aliaser;
    private ArrayList<TestCase> testCases;
    private ArrayList<TestCase> checkedTestCases = new ArrayList<>();
    private RecipesBuffer recipesBuffer =RecipesBuffer.getInstance();
    public TestCaseThread(AliaserImpl aliaser, ArrayList<TestCase> testCases) {
        this.testCases = testCases;
        this.aliaser=aliaser;
    }


    public void start() {
        System.out.println(testCases);
        for(TestCase testCase:testCases){
            int idL=testCase.getLeftId();
            int idR=testCase.getRightId();
            try{
                Recipe left = recipesBuffer.getRecipeById(idL);
                 Recipe right = recipesBuffer.getRecipeById(idR);
            int leftLength=aliaser.getSplitWords(left.getDescription()).length;
            int rightLength=aliaser.getSplitWords(right.getDescription()).length;
            if(leftLength==rightLength){
                int similarWords = aliaser.getSimilarWordsAmount(left.getDescription(),right.getDescription());
                int difference = leftLength - similarWords;
                if(difference<=Math.round(leftLength*TOLERANCE)){
                    this.checkedTestCases.add(new TestCase(
                            left.getId(),aliaser.getOneAliasFromAliasedMessage(left.getDescription()),left.getDescription(),
                            right.getId(),aliaser.getOneAliasFromAliasedMessage(right.getDescription()),right.getDescription()
                            ));
                }
            }
            }catch (Exception e){
                    e.printStackTrace();
            }
        }
    }


    private String[] getSplitWords(String s){
        String removedSpecialChars = removeSpecialCharacters(s);
        return removedSpecialChars.split(" ");
    }

    protected String removeSpecialCharacters(String s){
        return s.replaceAll("[-+.^:,()]","");

    }

    public ArrayList<TestCase> getCheckedTestCases() {
        return checkedTestCases;
    }
}
