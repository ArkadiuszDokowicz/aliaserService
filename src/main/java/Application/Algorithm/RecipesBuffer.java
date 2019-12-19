package Application.Algorithm;


import Application.Model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipesBuffer {


    public HashMap<Integer,Recipe> allRecipesNotHashed= new HashMap<Integer, Recipe>();
    private ArrayList<Recipe> allRecipesHashed= new ArrayList<>();
    private static RecipesBuffer single_instance = null;


    private RecipesBuffer(){}

    public synchronized void addNotHashedRecipes(ArrayList<Recipe> lista){
        for(Recipe recipe :lista){
            this.allRecipesNotHashed.put(recipe.getId(),recipe);
        }
    }
    public synchronized Recipe getRecipeById(int id) throws Exception {
        if(allRecipesNotHashed.containsKey(id)){
            return allRecipesNotHashed.get(id);
        }
        else throw new Exception("No recipe with required id: "+id);

    }
    public static RecipesBuffer getInstance()
    {
        if (single_instance == null)
            single_instance = new RecipesBuffer();

        return single_instance;
    }
    protected synchronized void addRecipes(ArrayList<Recipe> partOfRecipes){
        this.allRecipesHashed.addAll(partOfRecipes);
    }
    protected synchronized ArrayList<Recipe> getRecipes(){
        return allRecipesHashed;
    }
    public ArrayList<Recipe> getAllRecipesHashed() {
        return allRecipesHashed;
    }

}
