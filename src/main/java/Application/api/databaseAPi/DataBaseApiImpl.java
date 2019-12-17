package Application.api.databaseAPi;

import Application.Model.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DataBaseApiImpl implements DataBaseApiInterface {

    private final String PASSWORD = "maslo123";
    private final String DATA_BASE_URL = "http://192.168.1.70:8080/";
   // private final String DATA_BASE_URL = "http://localhost:8080";

    private final String GET_ALL_RECIPES = "recipe/all";
    private final String GET_RANGE_RECIPES = "recipe";
    private final String GET_TABLE_SIZE = "recipe/count";
    private final String ADD_RECIPE = "recipe/body";
    private final String ADD_ALL_ALIASES = "alias/all";
    private final String ADD_ALL_RECIPES= "recipe/replace";
    private static final String GET_TEST_CASE = "testcase";
    private final String ADD_ALL_TESTCASES="testcase/all";
    private final String ADD_TEST_CASE="testcase";
    private final String DELETE_ALIAS = "alias/delete/";
    private final String RECIPE_UPDATE= "recipe/replace";

    @Override
    public ArrayList<Recipe> getRecipesForRange(int first, int last) {
        RestTemplate restTemplate = new RestTemplate();
        String url = DATA_BASE_URL+GET_RANGE_RECIPES+"?startIndex="+first +"&endIndex="+last;
        System.out.println(url);
        ResponseEntity<List<Recipe>> rateResponse =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Recipe>>() {
                        });
        ArrayList<Recipe> recipes = (ArrayList<Recipe>) rateResponse.getBody();

        return  recipes;
    }


    @Override
    public void sendRecipes(ArrayList<Recipe> recipes) {
        try {
            String url =DATA_BASE_URL + ADD_ALL_RECIPES;
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(
                    url,
                    recipes,
                    ResponseEntity.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getDataBaseTableSize(String table) {
        RestTemplate restTemplate = new RestTemplate();
        String url = DATA_BASE_URL + GET_TABLE_SIZE;
        System.out.println(url);
        ResponseEntity<DbTableSize> response =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<DbTableSize>() {
                        });
        return Objects.requireNonNull(response.getBody()).getTableSize();
    }


    @Override
    public void sendAliases(ArrayList<Alias> aliases) {
         String url = DATA_BASE_URL + ADD_ALL_ALIASES;
         System.out.println(url);
         try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url,
                    aliases,
                    ResponseEntity.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendTestCases(ArrayList<TestCase> testCases) {
        try {
            String url = DATA_BASE_URL + ADD_ALL_TESTCASES;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(
                    url,
                    testCases,
                    ResponseEntity.class);
            System.out.println(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getDateBaseMemorySize() {
        RestTemplate restTemplate = new RestTemplate();
        String url = DATA_BASE_URL + GET_RANGE_RECIPES;
        ResponseEntity<DataBaseSize> response =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<DataBaseSize>() {
                        });
        return Objects.requireNonNull(response.getBody()).getSize();

    }

    @Override
    public TestCase getTestCase() {
        RestTemplate restTemplate = new RestTemplate();
        String url = DATA_BASE_URL + GET_TEST_CASE;
        System.out.println(url);
        TestCase testcase = restTemplate
                .getForObject(url, TestCase.class);
        return testcase;
    }

    @Override
    public void addRecipe(String name,String description,Boolean isVege) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Recipe> request = new HttpEntity<>(new Recipe(name,isVege,description));
        Recipe recipeFromDB = restTemplate.postForObject(DATA_BASE_URL+ADD_RECIPE,request,Recipe.class);
        //recipeFromDB.isVege();
    }

    @Override
    public void sendTestCase(TestCase testCase) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<TestCase> request = new HttpEntity<>(testCase);
        restTemplate.put(DATA_BASE_URL+ADD_TEST_CASE,request);
    }

    @Override
    public void deleteAlias(String alias) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(DATA_BASE_URL+DELETE_ALIAS+"?alias="+alias,"");
    }

    @Override
    public void updateRecipe(int id, String alias) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(DATA_BASE_URL+RECIPE_UPDATE+"?id="+id+"&alias="+alias, String.class);

    }

}
