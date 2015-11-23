package java;

import java.util.ArrayList;
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
            this(key, value, null);
        }

        Node(T1 key, T2 value, ArrayList<Node<T1, T2>> children) {
            this.key = key;
            this.value = value;
            this.children = children;
        }


        private Node<T1, T2> search(Node<T1, T2> ObjSearcher, ArrayList<Node<T1, T2>> ListSearcher) {
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


    }

    private Node<T1, T2> root = null;


    //Need to end
    private ArrayList<Node<T1, T2>> parser(String way) {
        ArrayList<Node<T1, T2>> tempArr = new ArrayList<>();
        int i = 0;
        int j = 0;
        String tempStr = "";
        T1 tempNode;
        for (int k = 0; k < way.length(); k++) {
            if (way.charAt(i) != '/') {
                tempStr += way.charAt(i);
            } else {
                tempNode = (T1) tempStr;
                // tempArr.add(j,tempNode.clone());    //Clone to T2(mby transform T2 to Object)
            }
        }

        return tempArr;

    }


    public void add(String way, T1 key, T2 value) {
        if (root != null) {
            ArrayList<Node<T1, T2>> wayArr = new ArrayList<>();
            wayArr = parser(way);
            Node<T1, T2> currentNode = root;
            for (int i = 0; i < wayArr.size(); i++) {
                currentNode = currentNode.search(wayArr.get(i), currentNode.children);
            }
            Node<T1, T2> Added = new Node<T1, T2>(key, value);
            currentNode.children.add(currentNode.children.size(), Added);
        } else {
            root.key = key;
            root.value = value;
            root.children = new ArrayList<>();
        }
    }

    public void delete(String way, T1 key) {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        } else {
            Node<T1,T2> currentNode = root;
            ArrayList<Node<T1,T2>> wayArr = new ArrayList<>();
            wayArr = parser(way);
            for(int i = 0; i < wayArr.size(); i++){
                currentNode = currentNode.search(wayArr.get(i), currentNode.children);
            }
            Node<T1,T2> objSearcher = new Node<T1, T2>(key,null);
            Node<T1,T2> deleted = currentNode.search(objSearcher,currentNode.children);
            deleted = null;
        }
    }



}
