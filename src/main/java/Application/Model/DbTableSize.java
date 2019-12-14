package Application.Model;

import java.io.Serializable;

public class DbTableSize implements Serializable {
    private int tableSize;

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public DbTableSize(int tableSize) {
        this.tableSize = tableSize;
    }
}
