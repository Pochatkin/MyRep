package com.example.julia.uley.client;
import com.example.julia.uley.common.Package;

import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;

import javax.net.ssl.SSLSocket;

/**
 * Created by Сергей on 04.12.2015.
 */
public class Listener implements Runnable {
    private Thread thread;
    private static final int BUFF_LEN = 1024 * 5;
    private InputStream inputStream;
    //private PackageService packageService;

    private ArrayDeque<Package> arrayDeque;

    public Listener(SSLSocket sslSocket, ArrayDeque<Package> arrayDeque){
        try {
            inputStream = sslSocket.getInputStream();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        this.arrayDeque = arrayDeque;

        //packageService = new PackageService();
        thread = new Thread(this, "ListenerThread");
        thread.start();
    }

    public void run() {
        listen();
    }

    private void listen() {
        try {
            byte[] buf = new byte[BUFF_LEN];
            int r = 0;
            String request;

            while (true) {
                try {
                    if ((r = inputStream.read(buf)) > 0) {
                        try {
                            Package receivedPack = Package.deserialize(buf);
                            //packageService.processPackage(receivedPack);
                            arrayDeque.add(receivedPack);
                        } catch (Exception e) {
                            System.out.println("failed to deserialize");
                        }
                    }
                } catch (SocketTimeoutException e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    class PackageService {
//        Package currentPackage;
//        SimpleDateFormat formatter;
//
//        PackageService() {
//            formatter = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
//        }
//
//        //обрабатывает пришедший пакет
//        void processPackage(Package pack) {
//            currentPackage = pack;
//
//            PackageType packType = pack.getType();
//            switch (packType) {
//                case REQ_SEND_MESSAGE:
//                    System.out.println("From: " + pack.getLogin() + " " + formatter.format(pack.getDate()) +
//                            "\n" + "-> " + pack.getMessage());
//                    break;
//
//                default:
//                    System.out.println(packType.name());
//            }
//        }
//    }
}
