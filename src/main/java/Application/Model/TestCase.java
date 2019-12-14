package Application.Model;

import java.io.Serializable;

public class TestCase implements Serializable {
    private int id;
    private int leftId;
    private String leftAlias;
    private String leftDescription;
    private int rightId;
    private String rightAlias;
    private String rightDescription;
    private int status =0;

    public TestCase(int id, int leftId, String leftAlias, String leftDescription, int rightId, String rightAlias, String rightDescription) {
        this.id = id;
        this.leftId = leftId;
        this.leftAlias = leftAlias;
        this.leftDescription = leftDescription;
        this.rightId = rightId;
        this.rightAlias = rightAlias;
        this.rightDescription = rightDescription;
    }

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
