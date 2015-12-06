package com.example.julia.uley.client;

/**
 * Created by Дмитрий on 18.10.2015.
 */

import java.io.*;
import java.net.*;

class SampleServer extends Thread {
    Socket s;
    int num;

    public static void main(String args[]) {
        try {
            int connectionsCounter = 0; // счётчик подключений

            // привинтить сокет на локалхост, порт 3128
            //ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("192.168.1.20"));
            ServerSocket server = new ServerSocket(3128);

            System.out.println("server is started");

            // слушаем порт
            while (true) {
                // ждём нового подключения, после чего запускаем обработку клиента
                // в новый вычислительный поток и увеличиваем счётчик на единичку
                new SampleServer(connectionsCounter, server.accept());
                connectionsCounter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SampleServer(int num, Socket s) {
        System.out.println("Someone connected");
        System.out.println("IP: "+ s.getInetAddress().toString() + "\nLocalPort: " + s.getLocalPort()
                            + "\nLocal IP: "+ s.getLocalAddress().toString());
        // копируем данные
        this.num = num;
        this.s = s;

        // и запускаем новый вычислительный поток (см. ф-ю run())
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run() {
        try {
            // из сокета клиента берём поток входящих данных
            InputStream is = s.getInputStream();
            // и оттуда же - поток данных от сервера к клиенту
            OutputStream os = s.getOutputStream();

            // буффер данных в 64 килобайта
            byte buf[] = new byte[64 * 1024];
            // читаем 64кб от клиента, результат - кол-во реально принятых данных
            int r = is.read(buf);

            // создаём строку, содержащую полученную от клиента информацию
            String data = "";
            if (r > 0) {
                data = new String(buf, 0, r);
            }

            // добавляем данные об адресе сокета:
            data = "" + num + ": " + "\n" + data;

            // выводим данные:
            os.write(data.getBytes());

            // завершаем соединение
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
