package Application.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AliasList implements Serializable {
    List<Alias> aliases;

    public List<Alias> getAliases() {
        return aliases;
    }

    public void setAliases(List<Alias> aliases) {
        this.aliases = aliases;
    }

    public AliasList(List<Alias> aliases) {
        this.aliases = aliases;
    }
}
