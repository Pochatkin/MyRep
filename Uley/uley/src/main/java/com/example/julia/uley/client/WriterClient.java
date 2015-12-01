package com.example.julia.uley.client;

import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Дмитрий on 19.10.2015.
 */
public class WriterClient {
    public static void main(String args[]) {
        try {
            Socket s = new Socket("192.168.1.20", 3128);
            Scanner scanner = new Scanner(System.in);
            String str;
            while (true) {
                str = scanner.nextLine();
                s.getOutputStream().write(str.getBytes());
            }

            // читаем ответ
//            byte buf[] = new byte[64*1024];
//            int r = s.getInputStream().read(buf);
//            String data = new String(buf, 0, r);
//
//            // выводим ответ в консоль
//            System.out.println(data);
        } catch (Exception e) {
            System.out.println("init error: " + e);
        } // вывод исключений
    }
}
