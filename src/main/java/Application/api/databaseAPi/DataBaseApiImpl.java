package Application.api.databaseAPi;

import Application.Model.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DataBaseApiImpl implements DataBaseApiInterface {
    private final String PASSWORD = "maslo123";
    private final String DATA_BASE_URL = "http://192.168.1.70:8080/";
    private final String GET_ALL_RECIPES = "recipe/all";
    private final String GET_RANGE_RECIPES = "recipe";
    private final String GET_TABLE_SIZE = "recipe/count";

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

    }

    @Override
    public void sendTestCases(ArrayList<TestCase> testCases) {

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
}
