import sun.tracing.NullProviderFactory;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Михаил on 03.10.2015.
 */
    public interface Methods {
    boolean isEmpty();
    void addF(Integer x);
    void addL(Integer x);
    int size();
    void deleteFirst() throws NullList;
    void deleteL() throws NullList;
    void deleteI(Integer index) throws NullList;
    void show();
    Iterator<Integer> iterator();
    BodyOfMethods map(Func func);

}
