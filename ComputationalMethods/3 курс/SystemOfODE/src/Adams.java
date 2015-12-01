import java.util.ArrayList;

/**
 * Created by Михаил on 01.12.2015.
 */
public class Adams {

    public ArrayList<double[]> adams() {
        ArrayList<double[]> Y = new ArrayList<>();
        double[] tempY = new double[2];
        double[][] W = new double[2][2];
        double h = 0.1;
        double x = 0;
        int k = 0;
        tempY[0] = tempY[1] = 1;
        W[0][0] = -0.86344095;
        W[0][1] = -0.16362877;
        W[1][0] = 1.022769285;
        W[1][1] = 0.18212395;
        while (x <= 0.5) {
            Y.add(k, tempY);
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

