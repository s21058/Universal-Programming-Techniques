/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T> {
    T fname;
    public InputConverter(T fname) {
        this.fname=fname;
    }
    public <T,R>R convertBy(Function... functions){
        List list = new ArrayList();
        list.add(functions[0].apply(fname));
        for (int i = 1; i < functions.length; i++) {
            list.add(functions[i].apply(list.get(i-1)));
        }
        return (R) list.get(list.size() -1 );
    }
}
