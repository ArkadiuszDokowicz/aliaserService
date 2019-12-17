package Application.Algorithm;

import Application.Model.Recipe;
import Application.api.databaseAPi.DataBaseApiImpl;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
@Deprecated
public class TestCaseThreadTest {
    @Mock
    DataBaseApiImpl dataBaseApi = Mockito.mock(DataBaseApiImpl.class);

    final int RECIPES_AMOUNT =105;
    //TODO
    @Test
    public void shouldCreateCheckedTestCases(){
        Mockito.when(dataBaseApi.getDataBaseTableSize("recipe")).thenReturn(105);
        ArrayList<Recipe> recipes= new ArrayList<>();
        for(int i=1;i<=RECIPES_AMOUNT;i++){
            recipes.add(new Recipe(i,"opis"+i,"tresc"+1,false));
        }

    }

}
