package Application.Algorithm;

import Application.Model.TestCase;

import java.util.ArrayList;

public class TestCasesBuffer {
    private ArrayList<TestCase> testCases = new ArrayList<>();
    private ArrayList<TestCase> goodTestCases= new ArrayList<>();
    private static TestCasesBuffer single_instance = null;

    private TestCasesBuffer(){}

    public static TestCasesBuffer getInstance()
    {
        if (single_instance == null)
            single_instance = new TestCasesBuffer();

        return single_instance;
    }

    public ArrayList<TestCase> getTestCases() {
        return testCases;
    }

    protected synchronized void addTestCases(ArrayList<TestCase> partOfRecipes){
        this.goodTestCases.addAll(partOfRecipes);
    }

}
