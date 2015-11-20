import java.util.Scanner;

/**
 * Created by Михаил on 03.10.2015.
 */
public class Main {
    public static void main(String args[]) throws NullList {
        Methods A = new BodyOfMethods();
        int k = 0;
        int temp = 0;
        Scanner in = new Scanner(System.in);
        class squared implements Func {
            @Override
            public Integer compile(Integer x) {
                return x * x;
            }
        }
        BodyOfMethods t = new BodyOfMethods();
        squared Squared = new squared();
        System.out.println("Enter number:");
        System.out.println("1. Add first element");
        System.out.println("2. Add last element");
        System.out.println("3. Delete first element");
        System.out.println("4. Delete last element");
        System.out.println("5. Delete element(index)");
        System.out.println("6. Show list");
        System.out.println("7. Size of list");
        System.out.println("8. Squared elements");
        System.out.println("9. Exit");
        while (k != 9) {
            System.out.println("Enter number:");
            k = in.nextInt();
            switch (k) {
                case 1: {
                    System.out.println("Enter value");
                    try {
                        temp = in.nextInt();
                    } catch (Exception e) {
                        break;
                    }

                    A.addF(temp);
                    break;
                }
                case 2: {
                    System.out.println("Enter value");
                    try {
                        temp = in.nextInt();
                    } catch (Exception e) {
                        System.out.println("jhbn");
                        break;
                    }

                    A.addL(temp);
                    break;
                }
                case 3: {
                    try {
                        A.deleteFirst();
                    } catch (NullList e) {
                        System.out.println("SADASDAS");
                    } finally {
                        break;
                    }
                }
                case 4: {
                    try {
                        A.deleteL();
                    } catch (NullList e) {
                        System.out.println("asda");
                    } finally {
                        break;
                    }
                }
                case 5: {
                    System.out.println("Enter index");


                    try {
                        temp = in.nextInt();
                    } catch (Exception e) {
                        System.out.println("asdas");
                    }


                    try {
                        A.deleteI(temp);
                    } catch (NullList e) {
                        System.out.println("dsadas");
                    } finally {
                        break;
                    }

                }
                case 6: {
                    A.show();
                    break;
                }
                case 7: {
                    System.out.println(A.size());
                }
                case 8: {
                    t = A.map(Squared);
                    t.show();
                }
                case 9: {
                    break;
                }
            }
        }
    }

}

