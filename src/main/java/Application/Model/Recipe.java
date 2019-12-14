package Application.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe implements Serializable {
    private int id;
    private String name;
    private String description;
    private boolean isVege;

    public Recipe(String name, String description, boolean isVege) {
        this.name = name;
        this.description = description;
        this.isVege = isVege;
    }

    public Recipe(int id, String name, String description, boolean isVege) {
        this.id = id;
        this.name = name;
        this.isVege = isVege;
        this.description = description;
    }

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVege() {
        return isVege;
    }

    public void setVege(boolean vege) {
        isVege = vege;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isVege=" + isVege +
                '}';
    }
}
