package Application.Services.Aliaser;

import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class Aliaser{
    
    private HashMap<String,String> aliases= new HashMap<>();
    private int hashed=0;
    private synchronized String  getNextHashedValue(){
        hashed++;
        return Integer.toHexString(hashed);
    }
    public Aliaser() {
    }

    public String getAliasedMessage(String text){
        String[] words=getSplitWords(text);
        String aliasedMessage=text;
        aliasedMessage=this.removeSpecialCharacters(aliasedMessage);
        for(String s:words){
            StringBuilder hashForReplace=new StringBuilder(" ");
            hashForReplace.append(getAlias(s));
            hashForReplace.append(" ");
            String foundedWord=" "+s+" ";
           aliasedMessage=aliasedMessage.replaceAll(foundedWord,hashForReplace.toString());
        }
        return aliasedMessage;
    }
    
    public HashMap<String, String> getAliases() {
        return aliases;
    }

    public void setAliases(HashMap<String, String> aliases) {
        this.aliases = aliases;
    }

    private String getAlias(String word) {

        if(this.aliases.containsKey(word)){
            return aliases.get(word);
        }
        else{
            aliases.put(word,"#"+getNextHashedValue());
            return aliases.get(word);
        }
    }
    
    private String[] getSplitWords(String s){
        String removedSpecialChars = removeSpecialCharacters(s);
        return removedSpecialChars.split(" ");
    }
    protected String removeSpecialCharacters(String s){
        return s.replaceAll("[-+.^:,]","");

    }
}
