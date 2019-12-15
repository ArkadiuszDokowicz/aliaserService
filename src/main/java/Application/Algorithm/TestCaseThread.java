package Application.Algorithm;

import Application.Model.Recipe;
import Application.Model.TestCase;
import Application.Services.Aliaser.AliaserImpl;

import java.util.ArrayList;

public class TestCaseThread   {
    private AliaserImpl aliaser;
    private ArrayList<TestCase> testCases;
    private ArrayList<TestCase> checkedTestCases = new ArrayList<>();
    public TestCaseThread(AliaserImpl aliaser, ArrayList<TestCase> testCases) {
        this.testCases = testCases;
        this.aliaser=aliaser;
    }

    public void start() {
        for(TestCase testCase: testCases){
            Recipe left=RecipesBuffer.getInstance().getRecipeById(testCase.getLeftId());
            Recipe right=RecipesBuffer.getInstance().getRecipeById(testCase.getRightId());
            int leftWordAmount=0,rightWordAmount=0;
            leftWordAmount=getSplitWords(left.getDescription()).length;
            rightWordAmount=getSplitWords(right.getDescription()).length;
            if(leftWordAmount == rightWordAmount){
                if(aliaser.getSimilarWordsAmount(left.getDescription(),right.getDescription())==leftWordAmount-1){
                    checkedTestCases.add(new TestCase(left.getId(),left.getDescription(),aliaser.revertAlias(left.getDescription()),
                            right.getId(),right.getDescription(),aliaser.revertAlias(right.getDescription())));
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
