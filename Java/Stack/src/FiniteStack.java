import java.util.Objects;
import java.util.Stack;

/**
 * Created by Михаил on 04.10.2015.
 */
public class FiniteStack<E> {

    private int FullSize;
    private Object[] stack;

    public FiniteStack() {
        FullSize = 10;
        stack = new Object[FullSize];
    }


    public boolean isFull() {
        return size() == FullSize;
    }


    public int size() {
        int i = 0;
        while (stack[i] != null) {
            i++;
            if (i == FullSize) {
                break;
            }
        }
        return i;
    }


    public void add(E value) throws OverflowException {
        if (!isFull()) {
            stack[size()] = value;
        } else {
            new OverflowException("irouep");
        }

    }

    public void show() {
        for (int i = size() - 1; i >= 0; i--) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }


    public void delete() {
        stack[size() - 1] = null;
    }


}
