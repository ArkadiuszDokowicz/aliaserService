package Application.Model;

import java.io.Serializable;

public class Alias implements Serializable {
    int id;
    String content;
    String alias;

    public Alias(int id, String content, String alias) {
        this.id = id;
        this.content = content;
        this.alias = alias;
    }

    public Alias(String content, String alias) {
        this.content = content;
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
