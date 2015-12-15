/**
 * Created by Михаил on 15.12.2015.
 */
public class Polynomial {

    private int degree;
    private double[] polynomial;

    public Polynomial(int degree,double[] polynomial){
        this.degree = degree;
        this.polynomial = polynomial;
    }

    public double[] getPolynomial(){
        return polynomial;
    }

    public int getDegree(){
        return degree;
    }
}
