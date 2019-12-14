package Application.Services.Aliaser;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AliaserImpl{

    private HashMap<String, Integer> wordFrequencyOfAppearance = new HashMap<>();
    private HashMap<String,String> aliases= new HashMap<>();
    private HashMap<String,String> words= new HashMap<>();
    private HashMap<String,String>messages = new HashMap<>();
    private int hashed=0;

    private synchronized String getNextHashedValue(){
        hashed++;
        return Integer.toHexString(hashed);
    }


    public HashMap<String, Integer> getWordFrequencyOfAppearance() {
        return wordFrequencyOfAppearance;
    }

    public HashMap<String, String> getAliases() {
        return aliases;
    }

    public void setAliases(HashMap<String, String> aliases) {
        this.aliases = aliases;
    }

    private synchronized String getAlias(String word) {

        if(this.aliases.containsKey(word)){
            return aliases.get(word);
        }
        else{
            String alias ="#"+ getNextHashedValue();
            words.put(alias,word);
            aliases.put(word,alias);
            return aliases.get(word);
        }
    }

    public AliaserImpl() {
    }
    public String getAliasedMessage(String text){
        String[] words= getSplitWords(text);
        String aliasedMessage=text;
        for(String s:words){
            countFrequencyOfAppearance(s);
            StringBuilder hashForReplace=new StringBuilder("");
            hashForReplace.append(getAlias(s));
           text=text.replaceFirst(s,hashForReplace.toString());
        }
        return text;
    }
    public String getOneAliasFromAliasedMessage(int id, String aliasedMessage){
     return getAliasFromMessage(id,aliasedMessage);
    }

    private synchronized String getAliasFromMessage(int id,String aliasedMessage) {
        if(this.aliases.containsKey(aliasedMessage)){
            return aliases.get(aliasedMessage);
        }
        else{
            String alias ="#"+ getNextHashedValue();
            messages.put(alias,aliasedMessage);
            aliases.put(aliasedMessage,alias);
            return aliases.get(aliasedMessage);
        }
    }

    private void countFrequencyOfAppearance(String word){
        if(wordFrequencyOfAppearance.containsKey(word)){
            Integer frequency = wordFrequencyOfAppearance.get(word);
            wordFrequencyOfAppearance.replace(word,frequency,frequency+1);
        }
        else{
            wordFrequencyOfAppearance.put(word,1);
        }
    }


    private Boolean isTheSameString(String first,String second){
        return first.equals(second);
    }

    protected int getSimilarWordsAmount(String first, String second){

        int similarWords=0;
        List<String> firstWordCollection = getSplitWordsAsList(first);
        List<String> secondWordCollection = getSplitWordsAsList(second);
        if(firstWordCollection.size()>=secondWordCollection.size()){
            for(String word:secondWordCollection)
            {
                int index= firstWordCollection.lastIndexOf(word);
                if(-1!=index){
                    similarWords++;
                    firstWordCollection.remove(index);
                };

            }
        }
        else{
            for(String word:firstWordCollection)
            {
                int index= secondWordCollection.lastIndexOf(word);
                if(-1!=index){
                    similarWords++;
                    secondWordCollection.remove(index);
                };

            }
        }
        return similarWords;
    }

    private List<String> getSplitWordsAsList(String s){
        List<String> stringsToReturn = new ArrayList<>();
        String[] strings= getSplitWords(s);
        for(String word:strings){
            stringsToReturn.add(word);
        }
        return stringsToReturn;
    }
    private String[] getSplitWords(String s){
        String removedSpecialChars = removeSpecialCharacters(s);
        return removedSpecialChars.split(" ");
    }
    protected String removeSpecialCharacters(String s){
        return s.replaceAll("[-+.^:,()]","");

    }
}
