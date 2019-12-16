package Application.Services.Aliaser;

import Application.Model.Alias;
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

    public ArrayList<Alias>getAliasesAsArrayList(){
        ArrayList<Alias> toReturn = new ArrayList<>();
        for (String word: aliases.keySet()){
            String key = word;
            String value = aliases.get(word);
            toReturn.add(new Alias(key,value));
        }
        return toReturn;
    }
    public AliaserImpl() {
    }
    public String getAliasedMessage(String text){
        String[] words= getSplitWords(text);
        String aliasedMessage=text;
        for(String s:words){
            countFrequencyOfAppearance(s);
            text=text.replaceFirst(s, "" + getAlias(s));
        }
        return text;
    }
    public String getOneAliasFromAliasedMessage(String aliasedMessage){
     return getAliasFromMessage(aliasedMessage);
    }

    private synchronized String getAliasFromMessage(String aliasedMessage) {
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

    public String revertAlias(String aliasedMessage){
        return getWords(getHashes(aliasedMessage));
    }
    private String getHashes(String hash){
       return messages.get(hash);
    }
    private String getWords(String hashes){
        String[] wordsSeparated= getSplitWords(hashes);
        String aliasedMessage=hashes;
        for(String s:wordsSeparated){
            countFrequencyOfAppearance(s);
            hashes=hashes.replaceFirst(s, "" + words.get(s));
        }
        return hashes;
    }
    private Boolean isTheSameString(String first,String second){
        return first.equals(second);
    }

    public int getSimilarWordsAmount(String first, String second){

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
    public String[] getSplitWords(String s){
        String removedSpecialChars = removeSpecialCharacters(s);
        return removedSpecialChars.split(" ");
    }

    protected String removeSpecialCharacters(String s){
        return s.replaceAll("[-+.^:,()]","");

    }
}
