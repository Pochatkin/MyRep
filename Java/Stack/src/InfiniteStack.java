import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by Михаил on 04.10.2015.
 */
public class InfiniteStack<E> {

    private int size;
    private E value;

    private InfiniteStack<E> last;
    private InfiniteStack<E> prev;


    public InfiniteStack() {
        value = null;
        last = null;
        size = 0;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E value) {
        if (!isEmpty()) {
            InfiniteStack<E> oldLast = last;
            last = new InfiniteStack<E>();
            last.value = value;
            last.prev = oldLast;
        } else {
            last = new InfiniteStack<>();
            last.value = value;
        }

        size++;
    }


    public void delete() {
        if (!isEmpty()) {
            last = last.prev;
            size--;
        } else {
            System.out.println("I can't do it");
        }

    }

    public void show() {
        InfiniteStack<E> temp = last;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.value + " ");
            temp = temp.prev;
        }
        System.out.println();
    }
}



