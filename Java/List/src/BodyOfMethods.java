import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.ConsoleHandler;

/**
 * Created by Михаил on 03.10.2015.List
 */
public class BodyOfMethods implements Methods {


    private static class List {
        private Integer value;
        private List next;
        private List prev;
    }

    private int size;
    private List first;
    private List last;

    public BodyOfMethods() {
        size = 0;
        first = null;
        last = null;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void addF(Integer value) {
        List oldFirst = first;

        first = new List();
        first.prev = null;
        first.value = value;


        if (isEmpty()) {
            first.next = null;
            last = first;
        } else {
            oldFirst.prev = first;
            first.next = oldFirst;
        }
        size++;

    }

    @Override
    public void addL(Integer value) {

        List oldLast = last;
        last = new List();

        last.value = value;
        last.next = null;

        if (isEmpty()) {
            last.next = null;
            first = last;
        } else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;

    }


    @Override
    public void deleteFirst() throws NullList {
        if (!isEmpty()) {
            first = first.next;
            size--;
        } else {
            throw new NullList("List is null");
        }

    }

    @Override
    public void deleteL() throws NullList {
        if (!isEmpty()) {
            last = last.prev;
            last.next = null;
            size--;
        } else {
            throw new NullList("List is null");
        }


    }


    @Override
    public void deleteI(Integer index) throws NullList {
        if (index >= size) {
            throw new NullList("Invalid index");
        } else {
            if (!isEmpty()) {

                List temp = first;
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                temp.next = null;
                temp.prev = null;
                size--;
            } else {
                throw new NullList("List is null");
            }
        }
    }


    @Override
    public Iterator<Integer> iterator() {
        return new ListIterator();
    }


    private class ListIterator implements Iterator {

        private List currentList = first;

        public boolean hasNext() {
            return currentList != null;
        }

        public Integer next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Integer item = currentList.value;
            currentList = currentList.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public BodyOfMethods map(Func func) {
        Iterator<Integer> iterator = iterator();
        BodyOfMethods temp = new BodyOfMethods();
        while (iterator.hasNext()) {
            temp.addL(func.compile((Integer) iterator.next()));
        }
        return temp;
    }


    @Override
    public void show() {
        Iterator<Integer> iterator = iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }


}
