/**
 * Created by Михаил on 15.12.2015.
 */
public class Function {

    private double p;
    private double q;
    private double r;
    private double f;

    public double getP(double x) {
        return ((6 + x) / (7 + 3 * x));
    }

    public double getQ(double x) {
        return (x / 2 - 1);
    }

    public double getR(double x) {
        return (1 + Math.cos(x) / 2);
    }

    public double getF(double x){
        return (1 - x / 3);
    }
}
