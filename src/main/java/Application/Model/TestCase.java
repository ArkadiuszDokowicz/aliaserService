package Application.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestCase implements Serializable {

    @JsonProperty("id")
    private int id;
    private int leftId;
    private String leftAlias;
    private String leftDescription;
    private int rightId;
    private String rightAlias;
    private String rightDescription;
    private int status;

    public TestCase(int id, int leftId, String leftAlias, String leftDescription, int rightId, String rightAlias, String rightDescription, int status) {
        this.id = id;
        this.leftId = leftId;
        this.leftAlias = leftAlias;
        this.leftDescription = leftDescription;
        this.rightId = rightId;
        this.rightAlias = rightAlias;
        this.rightDescription = rightDescription;
        this.status = status;
    }

    public TestCase(int leftId, int rightId) {
        this.leftId = leftId;
        this.rightId = rightId;
    }
    public TestCase(int id, int leftId, String leftAlias, String leftDescription, int rightId, String rightAlias, String rightDescription) {
        this.id = id;
        this.leftId = leftId;
        this.leftAlias = leftAlias;
        this.leftDescription = leftDescription;
        this.rightId = rightId;
        this.rightAlias = rightAlias;
        this.rightDescription = rightDescription;
    }

    public TestCase(int leftId, String leftAlias, String leftDescription, int rightId, String rightAlias, String rightDescription) {
        this.leftId = leftId;
        this.leftAlias = leftAlias;
        this.leftDescription = leftDescription;
        this.rightId = rightId;
        this.rightAlias = rightAlias;
        this.rightDescription = rightDescription;
    }

    public TestCase(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeftId() {
        return leftId;
    }

    public void setLeftId(int leftId) {
        this.leftId = leftId;
    }

    public String getLeftAlias() {
        return leftAlias;
    }

    public void setLeftAlias(String leftAlias) {
        this.leftAlias = leftAlias;
    }

    public String getLeftDescription() {
        return leftDescription;
    }

    public void setLeftDescription(String leftDescription) {
        this.leftDescription = leftDescription;
    }

    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
    }

    public String getRightAlias() {
        return rightAlias;
    }

    public void setRightAlias(String rightAlias) {
        this.rightAlias = rightAlias;
    }

    public String getRightDescription() {
        return rightDescription;
    }

    public void setRightDescription(String rightDescription) {
        this.rightDescription = rightDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
