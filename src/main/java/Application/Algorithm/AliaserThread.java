package Application.Algorithm;

import Application.Model.Recipe;
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
    AliaserThread(int first,int last,AliaserImpl aliaser ){
        this.firstIndex=first;
        this.lastIndex=last;
        this.aliaser=aliaser;
    };
    public void start(){
        recipes=dataBaseApi.getRecipesForRange(firstIndex,lastIndex);
        List<Recipe> aliasedRecipes= new ArrayList<Recipe>();
        for(Recipe r:recipes){
            String aliasedWords = aliaser.getAliasedMessage(r.getDescription());
            String aliasedMessage =aliaser.getOneAliasFromAliasedMessage(r.getId(),aliasedWords);
                    aliasedRecipes.add(new Recipe(r.getId(),r.getName(),aliasedMessage, r.isVege()));
        }
       }
}
