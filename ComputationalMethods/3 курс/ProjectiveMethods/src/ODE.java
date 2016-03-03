/**
 * Created by Михаил on 29.02.2016.
 */
public class ODE {



    public static final int a1 = 0;
    public static final int a2 = -1;
    public static final int b1 = 3;
    public static final int b2 = 2;

    double u;
    double v;
    double w;
    double f;
    public void ODE(double u,double v,double w,double f){
        this.u = u;
        this.v = v;
        this.w = w;
        this.f = f;
    }

    public double getF() {
        return f;
    }

    public double getU() {
        return u;
    }

    public double getV() {
        return v;
    }

    public double getW() {
        return w;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void setU(double u) {
        this.u = u;
    }

    public void setV(double v) {
        this.v = v;
    }

    public void setW(double w) {
        this.w = w;
    }
}

