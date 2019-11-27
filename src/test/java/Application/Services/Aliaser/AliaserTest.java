package Application.Services.Aliaser;

import org.junit.Test;

public class AliaserTest {
    @Test
    public void shouldReturnStringWithRemovedSpecialChars(){
        //when
        Aliaser aliaser = new Aliaser();
        //given
        String stringWithSpecialChars="Wlazł kotek na płotek i mruga, " +
                "ładna to piosenka, nie długa. Nie długa, nie krótka," +
                " lecz w sam raz, zaśpiewaj koteczku, jeszcze raz.";
        //then
        //TODO assertion
        System.out.println(aliaser.removeSpecialCharacters(stringWithSpecialChars));
    }
    @Test
    public void shouldReturnHashedMessage(){
        //when
        Aliaser aliaser= new Aliaser();
        //given
        String stringWithSpecialChars="Wlazł kotek na płotek i mruga, " +
                "ładna to piosenka, nie długa. Nie długa, nie krótka," +
                " lecz w sam raz, zaśpiewaj koteczku, jeszcze raz.";
        //then
        //TODO assertion
        System.out.println(aliaser.getAliasedMessage(stringWithSpecialChars));
    }
}
