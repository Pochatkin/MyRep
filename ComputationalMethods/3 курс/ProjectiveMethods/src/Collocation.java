/**
 * Created by Михаил on 29.02.2016.
 */
public class Collocation {
    public static final int n = 5;

    public static void main(String[] args) {

        ODE ode = new ODE();

        double[] t = new double[n];
        for (int i = 0; i < n; i++) {
            t[i] = Math.cos(Math.PI * (2 * i + 1) / 2 * n);
        }

    }
}
