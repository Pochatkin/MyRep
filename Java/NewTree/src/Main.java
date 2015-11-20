import java.util.Optional;

/**
 * Created by Михаил on 02.11.2015.
 */
public class Main {
    public static void main(String[] args) {
        BSTree<Integer, Integer> tree = new BSTree<>();
        tree.add(5,1);
        tree.add(6,2);
        tree.add(4,3);
        tree.add(10,4);
        Optional<Integer> value = tree.get(7);
        System.out.println(value);
//        tree.show();
    }
}
