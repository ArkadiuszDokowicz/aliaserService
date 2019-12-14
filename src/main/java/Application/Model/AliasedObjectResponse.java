package Application.Model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "text" })
public class AliasedObjectResponse{
        int id;
        String text;

public AliasedObjectResponse(int id, String text) {
        this.id = id;
        this.text = text;
        }

public int getId() {
        return id;
        }

public void setId(int id) {
        this.id = id;
        }

public String getText() {
        return text;
        }

public void setText(String text) {
        this.text = text;
        }
        }
