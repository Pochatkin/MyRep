import java.util.ArrayList;

/**
 * Created by Михаил on 18.11.2015.
 */
public class Main {
    public static void main(String[] args) {
        Tree<String, Integer> tree = new Tree<>();
        //     tree.add("root","root",5);
        //     tree.add("root","number1",3);
        //     tree.add("root","number2",4);
        //     tree.add("root/number2","number3",7);
        ArrayList<Tree.Node<String, Integer>> test = new ArrayList<>();
        test = tree.parser("root/number1/number2");
        System.out.println(test.get(0).key + " " + test.get(1).key + " " + test.get(2).key);
    }
}
