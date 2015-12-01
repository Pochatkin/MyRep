import java.util.ArrayList;

/**
 * Created by Михаил on 01.12.2015.
 */
public class Euler {
    public static final double eps = 0.01;
    ODE ode = new ODE();


    public ArrayList<double[]> euler() {
        //Scalar scalar = new Scalar();
        double lambda = -247.454;
        ArrayList<double[]> Y = new ArrayList<>();
        double[] tempY = new double[2];
        double[][] W = new double[2][2];
        double[][] E = new double[2][2];
        double h = 0.05;
        double x = 0;
        int k = 0;
        tempY[0] = tempY[1] = 1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == j) {
                    E[i][i] = 1;
                } else {
                    E[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                W[i][j] = E[i][j] - h * ode.getA()[i][j];
            }
        }
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
