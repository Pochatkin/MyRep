/**
 * Created by Михаил on 01.12.2015.
 */
public class Scalar {

    ODE ode = new ODE();

    public Scalar(){

    }
    public double doScalar() {
        double eps = 0.000001;
        double[] Y = new double[2];
        Y[0] = Y[1] = 1;
        double oldLambda = 0;
        double newLambda = 0;
        do {
            oldLambda = newLambda;
            newLambda = scal(multi(ode.getA(), Y), Y) / scal(Y, Y);
            Y = multi(ode.getA(), Y);
        }
        while (Math.abs(newLambda - oldLambda) > eps);
        return newLambda;
    }

    private double norm(double[] x)
    {
        return (Math.sqrt(x[0] * x[0] + x[1] * x[1]));
    }


    private double scal(double[] x, double[] y) {
        double temp = 0;
        for (int i = 0; i < 2; i++) {
            temp += x[i] * y[i];
        }
        return (temp);
    }

    private double[] multi(double[][] A, double[] y) {
        double[] temp = new double[2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                temp[i] += A[j][i] * y[j];
            }
        }
        return (temp);
    }


}
