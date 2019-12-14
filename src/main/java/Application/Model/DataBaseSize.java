package Application.Model;

import java.io.Serializable;

public class DataBaseSize implements Serializable {
    int size;

    public DataBaseSize(){};
    public DataBaseSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
