import java.util.ArrayList;

/**
 * Created by Михаил on 01.12.2015.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Euler");

        Euler euler = new Euler();
        ArrayList<double[]> listEuler = euler.euler();
        for (int i = 0; i < listEuler.size(); i++) {
            System.out.print("x = " + i * 0.1 + "    ");
            for (int j = 0; j < 2; j++) {
                System.out.print(listEuler.get(i)[j] + "    ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
        System.out.println();
        System.out.println("InvertEuler");
        InvertEuler invertEuler = new InvertEuler();
        ArrayList<double[]> listInvertEuler = invertEuler.euler();
        for (int i = 0; i < listInvertEuler.size(); i++) {
            System.out.print("x = " + i * 0.1 + "    ");
            for (int j = 0; j < 2; j++) {
                System.out.print(listInvertEuler.get(i)[j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
        System.out.println();
        System.out.println("Adams");
        Adams adams = new Adams();
        ArrayList<double[]> listAdams = adams.adams();
        for (int i = 0; i < listAdams.size(); i++) {
            System.out.print("x = " + i * 0.1 + "    ");
            for (int j = 0; j < 2; j++) {
                System.out.print(listAdams.get(i)[j] + " ");
            }
            System.out.println();
        }
        System.out.println();


    }
}
