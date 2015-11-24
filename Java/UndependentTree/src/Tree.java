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
            this(key, value,new ArrayList<Node<T1, T2>>());
        }

        Node(T1 key, T2 value, ArrayList<Node<T1, T2>> children) {
            this.key = key;
            this.value = value;
            this.children = children;
        }


        private T1 search(T1 ObjSearcher, ArrayList<Node<T1, T2>> ListSearcher) {
            T1 result = null;
            for (int i = 0; i < ListSearcher.size(); i++) {
                if (ObjSearcher.equals(ListSearcher.get(i).key)) {
                    result = ListSearcher.get(i).key;
                    break;
                }
            }
            if (result == null) {
                throw new NoSuchElementException("Incorrect way");
            } else {
                return result;
            }
        }


    }

    private Node<T1, T2> root = null;


    public ArrayList<T1> parser(String way) {
        ArrayList<T1> tempArr = new ArrayList<>();
        int j = 0;
        String tempStr = "";
        T1 tempT1;
        for (int k = 0; k < way.length(); k++) {
            if (way.charAt(k) != '/') {
                tempStr += way.charAt(k);
            } else {
                tempT1 = (T1) tempStr;
                tempArr.add(j,tempT1);
                j++;
                tempStr = "";
            }
        }
        return tempArr;
    }

    public void add(String way, T1 key, T2 value) {
        if (root != null) {
            ArrayList<T1> wayArr = new ArrayList<>();
            wayArr = parser(way);
            Node<T1, T2> currentNode = root;
            for (int i = 0; i < wayArr.size(); i++) {
                currentNode = currentNode.search(wayArr.get(i), currentNode.children);
            }
            Node<T1, T2> Added = new Node<T1, T2>(key, value);
            currentNode.children.add(currentNode.children.size(), Added);
        } else {
            Node<T1,T2> temp = new Node<T1, T2>(key,value);
            root = temp;
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
