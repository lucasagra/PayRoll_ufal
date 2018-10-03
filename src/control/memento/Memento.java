/**
 * This class implements the Memento Pattern
 */

package control.memento;

import control.utils.Clone;
import data.Data;

public class Memento {
    private Data state;

    public Memento(Data state){
        Data copy = (Data) Clone.deepCopy(state);
        this.state = copy;
    }

    public Data getState() {
        return state;
    }
}
