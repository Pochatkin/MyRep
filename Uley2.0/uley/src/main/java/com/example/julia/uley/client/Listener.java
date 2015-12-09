package com.example.julia.uley.client;

import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;

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

    //не тестили
    private ArrayDeque<Package> arrayDequeResp;
    private ArrayDeque<Package> arrayDequeReq;

    //не тестили
    public Listener(SSLSocket sslSocket, ArrayDeque<Package> arrayDequeResp, ArrayDeque<Package> arrayDequeReq){
        try {
            inputStream = sslSocket.getInputStream();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //не тестили
        this.arrayDequeResp = arrayDequeResp;
        this.arrayDequeReq = arrayDequeReq;

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

            while (true) {
                try {
                    if ((r = inputStream.read(buf)) > 0) {
                        try {
                            Package receivedPack = Package.deserialize(buf);

                            //не тестили
                            if(receivedPack.getType() == PackageType.REQ_SEND_MESSAGE) {
                                arrayDequeReq.add(receivedPack);
                            } else {
                                arrayDequeResp.add(receivedPack);
                            }
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
}
