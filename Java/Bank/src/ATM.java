import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Михаил on 25.09.2015.
 */
public class ATM {

    public static ArrayList<int[]> exchange(int sum, Integer[] ratigs) {
        ArrayList<int[]> temp = new ArrayList<>();
        int n = ratigs.length;
        int[] x = new int[n + 1];
        for (int i = 0; i < n; i++) {
            x[i] = 0;
        }
        do {
            x[0]++;
            if (summ(ratigs, x) == sum) {
                temp.add(x.clone());
            }
            for (int i = 0; summ(ratigs, x) > sum; i++) {
                x[i] = 0;
                x[i + 1]++;
            }
        }
        while (x[n] != 1);
        return (temp);
    }

    public static int summ(Integer[] ratigs, int[] x) {
        int n = ratigs.length;
        int s = 0;
        for (int i = 0; i < n; i++) {
            s += x[i] * ratigs[i];
        }
        return (s);
    }

}

