class Comp implements Comparable {

    String str;
    int number;

    Comp(String str, int number) {
        this.str = str;
        this.number = number;
    }

    @Override
    public int compareTo(Object obj) {
        Comp entry = (Comp) obj;

        int result = str.compareTo(entry.str);
        if(result != 0) {
            return result;
        }

        result = number - entry.number;
        if(result != 0) {
            return (int) result / Math.abs( result );
        }
        return 0;
    }

}
