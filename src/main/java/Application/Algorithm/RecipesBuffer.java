package Application.Algorithm;


import Application.Model.Recipe;

import java.util.ArrayList;

public class RecipesBuffer {

    private ArrayList<Recipe> allRecipesHashed= new ArrayList<>();
    private static RecipesBuffer single_instance = null;


    private RecipesBuffer(){}

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
