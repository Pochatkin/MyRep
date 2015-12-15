import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Михаил on 15.12.2015.
 */
public class Main {
    public static void main(String[] args) {
        ODE ode = new ODE();

        ode.start();

        System.out.println("i   x[i]     A[i]          B[i]       C[i]         G[i]     s[i]       t[i]    y[i]");
        for (int i = 0; i < ode.n + 1; i++) {
            System.out.println(i + "   " + new BigDecimal(ode.getX()[i]).setScale(3, RoundingMode.HALF_UP).doubleValue() + "   " + new BigDecimal(ode.getA()[i]).setScale(3, RoundingMode.HALF_UP).doubleValue()
                    + "     " + new BigDecimal(ode.getB()[i]).setScale(3,RoundingMode.HALF_UP).doubleValue() + "    " + new BigDecimal(ode.getC()[i]).setScale(3, RoundingMode.HALF_UP).doubleValue() +
                    "     " + new BigDecimal(ode.getG()[i]).setScale(3, RoundingMode.HALF_UP).doubleValue() + "    " + new BigDecimal(ode.getS()[i]).setScale(3,RoundingMode.HALF_UP).doubleValue() +
                    "      " + new BigDecimal(ode.getT()[i]).setScale(3, RoundingMode.HALF_UP).doubleValue() + "    " + new BigDecimal(ode.getY()[i]).setScale(4, RoundingMode.HALF_UP).doubleValue());
        }


    }
}
