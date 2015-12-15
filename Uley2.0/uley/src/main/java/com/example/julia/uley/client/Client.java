package com.example.julia.uley.client;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.julia.uley.common.Package;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayDeque;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Сергей on 20.11.2015.
 */
public class Client {
    private static final int TIMEOUT = 500;
    private static final int PORT = 3128;

    private ArrayDeque<Package> arrayDequeResp;
    private ArrayDeque<Package> arrayDequeReq;

    private Sender sender;
    private static Client ourInstance = new Client();
    private boolean inited;
    private Context context;

    private SSLSocketFactory socketFactory;
    private SSLSocket sslSocket;

    public static Client getInstance() {
        return ourInstance;
    }

//Здесь был контекст!!
    private Client() {

    }

    public void init(Context context) {
        if (inited) return;
        try {
            this.context = context;
            socketFactory = getSocketFactory();
            sslSocket = (SSLSocket) socketFactory.createSocket("172.20.205.136", PORT);
            sslSocket.setSoTimeout(TIMEOUT); //ждем ответа TIMEOUT миллисек
            //не тестили
            arrayDequeResp = new ArrayDeque<Package>();
            arrayDequeReq = new ArrayDeque<Package>();
            //не тестили
            new Listener(sslSocket, arrayDequeResp, arrayDequeReq);
            sender = new Sender(sslSocket);
            inited = true;
        } catch (Exception e) {
            System.out.println("failed to init client");
            e.printStackTrace();
        }
    }

    public void send(Package aPackage) throws Exception {
        if (!inited) throw new RuntimeException("cant send --- not inited");
        System.out.println("sender null: " + (sender == null));
        sender.sendPackage(aPackage);
    }


    //не тестили
    public Package getPackageResp() {
        if (!inited) throw new RuntimeException("cant getresp --- not inited");
        if (arrayDequeResp.isEmpty()) return null;

        Package pack = arrayDequeResp.getFirst();
        arrayDequeResp.removeFirst();
        return pack;
    }

    //не тестили
    public Package getPackageReq() {
        if (!inited) throw new RuntimeException("cant getreq --- not inited");
        if (arrayDequeReq.isEmpty()) return null;

        Package pack = arrayDequeReq.getFirst();
        arrayDequeReq.removeFirst();
        return pack;
    }

    private SSLSocketFactory getSocketFactory() throws Exception {
        KeyStore ks = KeyStore.getInstance("BKS");
        AssetManager am = context.getAssets();
        try {
            InputStream trustStream = am.open("client.bks");
            ks.load(trustStream, null);
        } catch (FileNotFoundException e) {
            System.out.println("srt bad");
            CertificateFactory crtFactory = CertificateFactory.getInstance("X.509");
            System.out.println("crt ok" + crtFactory.toString());
            InputStream is = am.open("");
            System.out.println(is.toString());
            Certificate cert = crtFactory.generateCertificate(is);
            ks.load(null, null);
            ks.setCertificateEntry("server", cert);
            //FileOutputStream fos = new FileOutputStream(tsName);
            //ks.store(fos, "123456".toCharArray());
            //fos.close();
        }

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManager[] trustManagers = tmf.getTrustManagers();
        sslContext.init(null, trustManagers, null);
//        sslContext.init(null, null, null);
        return sslContext.getSocketFactory();
    }


//    public static void main(String[] args) {
//        try {
//            Client client = new Client();
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
}
