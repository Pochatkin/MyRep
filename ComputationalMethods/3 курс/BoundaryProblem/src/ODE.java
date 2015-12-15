/**
 * Created by Михаил on 15.12.2015.
 */
public class ODE {
    Function function = new Function();
    public static final int n = 20;
    public static final double a = -1;
    public static final double b = 1;
    private double h = (b - a) / n;


    private double[] A = new double[n + 1];
    private double[] B = new double[n + 1];
    private double[] C = new double[n + 1];
    private double[] G = new double[n + 1];
    private double[] x = new double[n + 1];
    private double[] s = new double[n + 1];
    private double[] t = new double[n + 1];
    private double[] y = new double[n + 1];

    public ODE(){

    }

    public void start(){
        setX();
        setA();
        setB();
        setC();
        setG();
        setTandSetS();
        setY();
    }


    private void setX() {
        for (int i = 0; i < n + 1; i++) {
            x[i] = a - (h / 2) + i * h;
        }
    }


    private void setY() {
        y[n] = t[n];
        for(int i = n - 1; i >= 0; i--){
            y[i] = s[i] * y[i + 1] + t[i];
        }
    }

    private void setTandSetS() {
        t[0] = -G[0] / B[0];
        s[0] = C[0] / B[0];
        for (int i = 1; i < n + 1; i++) {
            t[i] = (A[i] * t[i - 1] - G[i]) / (B[i] - A[i] * s[i - 1]);
            s[i] = C[i] / (B[i] - A[i] * s[i - 1]);
        }
    }

    private void setG() {
        for (int i = 1; i < n ; i++) {
            G[i] = function.getF(x[i]);
        }
        G[0] = G[n] = 0;
    }

    private void setC() {
        for (int i = 1; i < n; i++) {
            C[i] = -function.getP(x[i]) / (h * h) + function.getQ(x[i]) / (2 * h);
        }
        C[0] = (-1 + 1 / h);
        C[n] = 0;
    }

    private void setB() {
        for (int i = 1; i < n; i++) {
            B[i] = -(2 * function.getP(x[i]) / (h * h) + function.getR(x[i]));
        }
        B[0] = -(-1 - 1 / h);
        B[n] = -(1 / h);
    }

    private void setA() {
        A[0] = 0;
        for (int i = 1; i < n; i++) {
            A[i] = -function.getP(x[i]) / (h * h) - function.getQ(x[i]) / (2 * h);
        }
        A[n] = (-1 / h);
    }


    public double[] getA() {
        return A;
    }

    public double[] getB() {
        return B;
    }

    public double[] getC() {
        return C;
    }

    public double[] getG() {
        return G;
    }

    public double[] getX() {
        return x;
    }

    public double[] getS() {
        return s;
    }

    public double[] getT() {
        return t;
    }

    public double[] getY() {
        return y;
    }


}
