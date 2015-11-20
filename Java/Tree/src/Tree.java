/**
 * Created by Михаил on 24.10.2015.
 */
public class Tree<E> implements Comparable {
    private Tree first = null;
    private Tree current;
    private E value;
    private Tree parent;
    private Tree left;
    private Tree right;

    private Integer length = 0;


    public Tree() {
        this.first = first;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Object obj) {
        Tree tmp = (Tree) obj;
        int result = current.compareTo(current.value);
        if (result != 0) {
            return result;
        }
        return 0;
    }

    public Integer size() {
        return length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(int value) {
        Tree temp = new Tree();
        Tree temp2 = new Tree();
        int i = 0;
        if (!isEmpty()) {
            while (temp != null) {
                if (compareTo(temp) == 1) {
                    if (temp.right != null) {
                        temp2 = temp;
                        temp = temp.right;
                    } else {
                        break;
                    }
                }
                if (compareTo(temp) == -1) {
                    if (temp.left != null) {
                        temp2 = temp;
                        temp = temp.left;
                    } else {
                        break;
                    }
                }
                i++;
            }

            temp.value = value;
            temp.parent = temp2;
        }
        if (i > length) {
            length++;
        }

    }

    public void delete() {
    }

    public void search() {

    }

    public void shift(Tree obj){

    }

    public void show(){
        Tree tmp = first;
        while(tmp != null){
            System.out.println(tmp);

        }
    }

}
