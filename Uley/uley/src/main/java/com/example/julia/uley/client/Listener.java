package com.example.julia.uley.client;

import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;

import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;

import javax.net.ssl.SSLSocket;

/**
 * Created by Сергей on 04.12.2015.
 */
public class Listener {
    private static final int BUFF_LEN = 1024 * 5;
    private InputStream inputStream;
    private PackageService packageService;

    public Listener(SSLSocket sslSocket) {
        try {
            inputStream = sslSocket.getInputStream();
        } catch (Exception e) {
            //
        }
        packageService = new PackageService();
    }

    public Package start() {
        return listen();
    }

    private Package listen() {
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
                            return receivedPack;

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
        return null;
    }

    class PackageService {
        Package currentPackage;
        SimpleDateFormat formatter;

        PackageService() {
            formatter = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        }

        //обрабатывает пришедший пакет
        void processPackage(Package pack) {
            currentPackage = pack;

            PackageType packType = pack.getType();
            switch (packType) {
                case REQ_SEND_MESSAGE:
                    System.out.println("From: " + pack.getLogin() + " " + formatter.format(pack.getDate()) +
                            "\n" + "-> " + pack.getMessage());
                    break;

                default:
                    System.out.println(packType.name());
            }
        }
    }
}
