/**
 * Created by Михаил on 01.12.2015.
 */
public class ODE {
    private double[][] A = new double[2][2];
    private double[] y = new double[2];

    public ODE(){
        A[0][0] = -125;
        A[0][1] = 123.45;
        A[1][0] = 123.45;
        A[1][1] = 123;
        y[0] = y[1] = 1;
    }

    public double[][] getA(){
        return A;
    }

}
