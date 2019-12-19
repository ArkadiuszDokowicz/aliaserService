package Application.Algorithm;

import Application.Model.Recipe;
import Application.Model.TestCase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TestCaseProducer{
    private int dbSize;
    private ArrayList<TestCase> testCases = new ArrayList<>();

    public ArrayList<TestCase> getTestCases() {
        return testCases;
    }

    public TestCaseProducer(int dbSize) {
        this.dbSize = dbSize;
        this.createPossiblyTestCases();
    }

    protected void createPossiblyTestCases(){
        for(int x =1 ;x <=dbSize ;x++) {
            for (int y = 1; y <= dbSize; y++) {
                if (x != y && x < y) {
                    testCases.add(new TestCase(x, y));
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder toReturn=new StringBuilder();
        for(TestCase t:testCases){
            toReturn.append(" {"+t.getLeftId()+" "+t.getRightId()+ "}");
        }
        return toReturn.toString();
    }


}
