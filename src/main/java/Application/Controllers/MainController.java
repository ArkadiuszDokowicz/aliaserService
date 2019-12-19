package Application.Controllers;

import Application.Model.AliasedObjectResponse;
import Application.Model.Recipe;
import Application.Services.Aliaser.AliaserImpl;
import Application.api.databaseAPi.DataBaseApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/AliaserPRO")
public class MainController {

    @Autowired
    AliaserImpl aliaser;
    @Autowired
    DataBaseApiImpl dataBaseApi;

    @GetMapping(value = "SendRecord", produces = "application/json")
    public Object makeAlias(@RequestParam("id") int id, @RequestParam("text") String text) {
        String aliasedText = aliaser.getAliasedMessage(text);
        return new AliasedObjectResponse(id, aliasedText);
    }

    @GetMapping(value = "test", produces = "application/json")
    public String greetings() {
        return new StringBuilder("Xd").toString();
    }

    @GetMapping(value = "apiTest", produces = "application/json")
    public Object testApi() {
        return dataBaseApi.getRecipesForRange(1, 2);
    }
    @GetMapping(value = "apiTest2", produces = "application/json")
    public Object testApi2() {
       RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Recipe> request = new HttpEntity<>(new Recipe("test",false,"test"));
        Recipe recipe = restTemplate.postForObject("http://192.168.1.70:8080/recipes/body",request,Recipe.class);
        return request.getBody().toString();
    }

}
