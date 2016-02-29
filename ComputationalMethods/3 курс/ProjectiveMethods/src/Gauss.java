/**
 * Created by Михаил on 29.02.2016.
 */
public class Gauss {
    private int[][] a;

    public int[][] getA() {
        return a;
    }

    public void setA(int[][] a) {
        this.a = a;
    }

    private int Check(int x){
        if (x == 0)
        {
            Check(x);
            return (x);
        }
        else
        {
            return (x);
        }
    }

    private int[] MethodOfGauss(){
        int[] temp = new int[a.length];
        for(int i = 0; i < a.length; i++){
            for(int j = i; j < a.length; j++){
                a[i,j] =
            }
        }
        return temp;
    }
}
