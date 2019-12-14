package Application.Services.Aliaser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AliaserTest {
    @Test
    public void shouldReturnStringWithRemovedSpecialChars(){
        //when
        AliaserImpl aliaser = new AliaserImpl();
        //given
        String stringWithSpecialChars="Wlazł kotek na płotek i mruga, " +
                "ładna to piosenka, nie długa. Nie długa, nie krótka," +
                " lecz w sam raz, zaśpiewaj koteczku, jeszcze raz.";
        //then
        assertFalse(aliaser.removeSpecialCharacters(stringWithSpecialChars).contains(","));
    }
    @Test
    public void shouldReturnHashedMessage(){
        //when
        AliaserImpl aliaser= new AliaserImpl();
        //given
        String stringWithSpecialChars="Wlazł kotek na płotek i mruga, " +
                "ładna to piosenka, nie długa. Nie długa, nie krótka," +
                " lecz w sam raz, zaśpiewaj koteczku, jeszcze raz.";
        //then
        String expected ="#1 #2 #3 #4 #5 #6, #7 #8 #9, #a #b. #c #b, #a #d, #e #f #10 #11, #12 #13, #14 #11.";
        assertEquals(expected,aliaser.getAliasedMessage(stringWithSpecialChars));
    }
    @Test
    public void shouldRecognizeBracesInText(){
        //given
        AliaserImpl aliaser=new AliaserImpl();
        //when
        String textToBeAliased="(NHMUK R3078)";
        //then
        String expected ="NHMUK R3078";
        assertEquals(expected,aliaser.removeSpecialCharacters(textToBeAliased));
    }
    @Test
    public void shouldCountWordFrequencyOfAppearance(){
        //given
        AliaserImpl aliaser = new AliaserImpl();
        //when
        String textToBeAliased="Potrawa jest bardzo dobra, ale ostra. Potrawa jest wizytówką kuchni meksykańskiej.";
        aliaser.getAliasedMessage(textToBeAliased);
        //then
        Integer expected = 2;
        assertEquals(expected,aliaser.getWordFrequencyOfAppearance().get("Potrawa"));
    }
    @Test
    public void shouldReturnLastIndexOfObjectFromList(){
        //given
        List<String> secondWordCollection = new ArrayList<>();
        //when
        secondWordCollection.add("siema");
        secondWordCollection.add("siema");
        secondWordCollection.add("siema");
        secondWordCollection.add("siema");
        //then
        int expected = 3;
        assertEquals(expected,secondWordCollection.lastIndexOf("siema"));
    }
    @Test
    public void shouldReturnSimilarWordAmount(){
        //given
        String first,second;
        AliaserImpl aliaser;
        //when
        first = "recipe contains bread, butter and an egg";
        second =  "recipe contains nothing cause its 1k calories diet";
        aliaser = new AliaserImpl();
        //then
        double  expected = 2;
        double delta = 0.001;
        assertEquals(expected,aliaser.getSimilarWordsAmount(first,second),delta);
    }
   /* @Test
    public void shouldReturnSimilarWordAmount(){
        //given
        String first,second;
        AliaserImpl aliaser;
        //when
        first = "recipe contains bread, butter and an egg";
        second =  "recipe contains nothing cause its 1k calories diet";
        aliaser = new AliaserImpl();
        //then
        double  expected = 2;
        double delta = 0.001;
        assertEquals(expected,aliaser.getSimilarity(first,second),delta);
    }
    */
}
