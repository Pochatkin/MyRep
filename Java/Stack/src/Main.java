import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михаил on 04.10.2015.
 */
public class Main {
    public static void main(String[] args) throws OverflowException {
        InfiniteStack<Integer> infiniteStack = new InfiniteStack<>();
        for (int i = 0; i < 10; i++) {
            infiniteStack.add(i + 10);
        }
        infiniteStack.show();
        for (int i = 0; i < 5; i++) {
            infiniteStack.delete();
        }

        infiniteStack.show();
        System.out.println();


        FiniteStack<Integer> finiteStack = new FiniteStack<>();
        for (int i = 0; i < 10; i++) {
            try {
                finiteStack.add(i);
            } catch (OverflowException e) {
                System.out.println("asdad");
            }
        }
        finiteStack.show();
        for (int i = 0; i < 5; i++) {
            finiteStack.delete();
        }
        finiteStack.show();
    }
}
