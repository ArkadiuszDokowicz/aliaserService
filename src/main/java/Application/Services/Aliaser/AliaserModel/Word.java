package Application.Services.Aliaser.AliaserModel;

public class Word {
    private String plainText;
    private String alias;
    int frequencyOfAppearance;

    public Word(String plainText, String alias) {
        this.plainText = plainText;
        this.alias = alias;
        this.frequencyOfAppearance=1;
    }

    public synchronized void incrementWordFrequencyOfAppearance(){
        this.frequencyOfAppearance++;
    }

    @Override
    public String toString() {
        return "{" +
                "plainText='" + plainText + '\'' +
                ", alias='" + alias + '\'' +
                ", frequencyOfAppearance=" + frequencyOfAppearance +
                '}';
    }
}
