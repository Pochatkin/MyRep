package java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Created by Михаил on 18.11.2015.
 */

public class Tree<T1 extends Comparable<T1>, T2> {

    static class Node<T1, T2> {
        T1 key;
        T2 value;
        ArrayList<Node<T1, T2>> children;

        Node(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<T1, T2> root = null;

    private ArrayList<Node<T1, T2>> parser(String way) {
        ArrayList<Node<T1, T2>> tempArr = new ArrayList<>();
        int i = 0;
        int j = 0;
        String tempStr = "";
        Node<T1, T2> tempNode;
        while (way.charAt(i) != '/') {
            tempStr += way.charAt(i);
        }
        tempNode =
                tempArr.add();

        return tempArr;

    }

    private Node<T1, T2> Search(Node<T1, T2> ObjSearcher, ArrayList<Node<T1, T2>> ListSearcher) {
        Node<T1, T2> result = new Node<>(null, null);
        for (int i = 0; i < ListSearcher.size(); i++) {
            if (ObjSearcher.equals(ListSearcher.get(i))) {
                result = ListSearcher.get(i);
                break;
            }
        }
        if (result.key.equals(null)) {
            throw new NoSuchElementException("Incorrect way");
        } else {
            return result;
        }
    }


    public void add(String way, T2 value) {
        ArrayList<Node<T1, T2>> wayArr = new ArrayList<>();

    }

}
