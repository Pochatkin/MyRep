import java.util.ArrayList;

/**
 * Created by Михаил on 01.12.2015.
 */
public class InvertEuler {
    public static final double eps = 0.01;
    public static final double lambda = -247.454;
    ODE ode = new ODE();


    public ArrayList<double[]> euler() {

        ArrayList<double[]> Y = new ArrayList<>();
        double[] tempY = new double[2];
        double[][] W = new double[2][2];
        double h = 0.1;
        double x = 0;
        int k = 0;
        tempY[0] = tempY[1] = 1;
        W[0][0] = 0.037055;
        W[0][1] =  W[1][0] = -0.0404821;
        W[1][1] = 0.044269;
        while (x <= 0.5) {
            Y.add(k,tempY);
            k++;
            tempY = multi(W, tempY);
            x += h;
        }
        return Y;
    }

    private double[] multi(double[][] A, double[] y) {
        double[] temp = new double[2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                temp[i] += A[j][i] * y[j];
            }
        }
        return temp;
    }
}
