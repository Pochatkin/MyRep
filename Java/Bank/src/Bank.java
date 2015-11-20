import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Михаил on 10.09.2015.
 */


public class Bank {
    public static ArrayList<int[]> result = new ArrayList<>();

    public static void main(String[] args) {
        String s = null;
        int sum = 0;
        int amount = 0;
        Scanner in = new Scanner(System.in);
        s = in.nextLine();
        ArrayList<Integer> x = new ArrayList<>();
        Scanner str = new Scanner(s);
        while (str.hasNext()) {
            try {
                x.add(str.nextInt());
            } catch (Exception e) {
                System.out.println("Неверный символ");
                return;
            }

        }
        sum = x.get(0);
        amount = x.size();
        int k = 0;
        Integer[] ratigs = (x.subList(1, amount)).toArray(new Integer[amount - 1]);
        for (int i = 0; i < ratigs.length; i++) {
            if (ratigs[i] <= 0) {
                k++;
            }
        }

        if (k == 0) {
            result = ATM.exchange(sum, ratigs);


            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < ratigs.length; j++) {
                    System.out.print(result.get(i)[j] + "x" + ratigs[j] + " ");
                }
                System.out.println();
            }
        }
        else
        {
            System.out.print("Ratigs is negative ");
        }
    }




}







