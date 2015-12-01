package com.example.julia.uley.client;

/**
 * Created by Дмитрий on 18.10.2015.
 */
import java.net.Socket;

class SampleClient extends Thread
{
    public static void main(String args[])
    {
        try
        {
            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            //Socket s = new Socket("92.42.31.144", 3128);
            Socket s = new Socket("localhost", 3128);

            // берём поток вывода и выводим туда первый аргумент
            // заданный при вызове, адрес открытого сокета и его порт

            s.getOutputStream().write("Hello".getBytes());

            // читаем ответ
            byte buf[] = new byte[64*1024];
            int r = s.getInputStream().read(buf);
            String data = new String(buf, 0, r);

            // выводим ответ в консоль
            System.out.println(data);
            //s.shutdownOutput();
            //s.close();
            Thread.sleep(10000);
            s.close();
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
}
