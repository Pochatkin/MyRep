package Util;

import java.util.Scanner;

/**
 * Created by Sergey on 18.03.2016.
 */
public class ForTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Person Anna = new Person("Anna");
        Person Nick = new Person("Nick");

        Game game = new Game(Anna, Nick);

//        AI AnnaAI = new AI(Anna, Color.WHITE);
//        AI NickAI = new AI(Nick, Color.BLACK);
//
//        int i = 0;
//
//        try {
//            while (true) {
//                i += 2;
//                Move t1 = AnnaAI.out();
//                System.out.println(t1.getPerson().name + " " + t1.getFrom().name() + "--" + t1.getTo().name());
//                game.doIt(t1);
//                NickAI.in(game.getLastMove());
//                System.out.println(game);
//
//                Move t2 = NickAI.out();
//                System.out.println(t2.getPerson().name + " " + t2.getFrom().name() + "--" + t2.getTo().name());
//                game.doIt(t2);
//                AnnaAI.in(game.getLastMove());
//                System.out.println(game);
//
//                //Thread.sleep(100);
//
//            }
//        } catch (Exception e) {
//            System.out.println(i);
//            e.printStackTrace();
//        }





//        game.doIt(Move.goFromTo(Anna,Cell.E2,Cell.E4)); System.out.println(game);
//        game.doIt(Move.goFromTo(Nick,Cell.C7,Cell.C5)); System.out.println(game);
//        game.doIt(Move.goFromTo(Anna,Cell.E1,Cell.E2)); System.out.println(game);
//        game.doIt(Move.goFromTo(Nick,Cell.A7,Cell.A5)); System.out.println(game);
//        game.doIt(Move.goFromTo(Anna,Cell.E2,Cell.D3)); System.out.println(game);
//        game.doIt(Move.goFromTo(Nick,Cell.B8,Cell.C6)); System.out.println(game);
//        game.doIt(Move.goFromTo(Anna,Cell.D1,Cell.F3)); System.out.println(game);
//        game.doIt(Move.goFromTo(Anna,Cell.F3,Cell.F5)); System.out.println(game);

        //Scholar's mate
        game.doIt(Move.goFromTo(Anna,Cell.E2,Cell.E4)); System.out.println(game);
        game.doIt(Move.goFromTo(Nick,Cell.E7,Cell.E5)); System.out.println(game);
        game.doIt(Move.goFromTo(Anna,Cell.F1,Cell.C4)); System.out.println(game);
        game.doIt(Move.goFromTo(Nick,Cell.B8,Cell.C6)); System.out.println(game);
        game.doIt(Move.goFromTo(Anna,Cell.D1,Cell.H5)); System.out.println(game);
        game.doIt(Move.goFromTo(Nick,Cell.G8,Cell.F6)); System.out.println(game);
        game.doIt(Move.goFromTo(Anna,Cell.H5,Cell.F7)); System.out.println(game);

        while (true) {

            String str1 = sc.nextLine();
            String from1 = str1.substring(0,2);
            String to1 = str1.substring(3,5);
            game.doIt(Move.goFromTo(Nick,Cell.valueOf(from1),Cell.valueOf(to1))); System.out.println(game);

            String str2 = sc.nextLine();
            String from2 = str2.substring(0,2);
            String to2 = str2.substring(3,5);
            game.doIt(Move.goFromTo(Anna,Cell.valueOf(from2),Cell.valueOf(to2))); System.out.println(game);
        }

    }
}
