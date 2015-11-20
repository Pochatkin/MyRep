package java;

import java.util.Collection;

/**
 * Created by Михаил on 18.11.2015.
 */

public class Tree<T1 extends Comparable<T1>,T2> {

    static class Node<T1, T2> {
        T1 key;
        T2 value;
        Node<T1, T2> left, right;

        Node(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<T1, T2> root = null;


    public void add(T1 key, T2 value){
        Node<T1,T2> x = root, y = null;

    }

}
