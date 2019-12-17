package Application.api.databaseAPi;

import Application.Model.Alias;
import Application.Model.Recipe;
import Application.Model.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface DataBaseApiInterface {

    ArrayList<Recipe> getRecipesForRange(int first, int last);

    void sendRecipes(ArrayList<Recipe> recipes);

    int getDataBaseTableSize(String table);

    void sendAliases(ArrayList<Alias> aliases);

    void sendTestCases(ArrayList<TestCase> testCases);

    int getDateBaseMemorySize();

    TestCase getTestCase();

    void addRecipe(String name, String description, Boolean isVege);

    void sendTestCase(TestCase testCase);

    void deleteAlias(String alias);

    void updateRecipe(int id,String alias);
}
