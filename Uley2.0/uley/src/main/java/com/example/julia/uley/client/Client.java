package com.example.julia.uley.client;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.julia.uley.common.Package;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class Client implements Serializable {
    private static String tsName = "src/res/client_key_store.jks";
    private static String ServerCrtName = "src/res/server_bks.cer";
    private static final int TIMEOUT = 500;
    private static final int PORT = 3128;

    private ArrayDeque<Package> arrayDequeResp;
    private ArrayDeque<Package> arrayDequeReq;

    private Sender sender;
    private static Context context;

    private SSLSocketFactory socketFactory;
    private SSLSocket sslSocket;


    public Client(Context context) throws Exception {
        this.context = context;
        socketFactory = getSocketFactory();


        sslSocket = (SSLSocket) socketFactory.createSocket("92.42.31.144", PORT);
        sslSocket.setSoTimeout(TIMEOUT); //ждем ответа TIMEOUT миллисек
        //не тестили
        arrayDequeResp = new ArrayDeque<Package>();
        arrayDequeReq = new ArrayDeque<Package>();
        //не тестили
        new Listener(sslSocket, arrayDequeResp, arrayDequeReq);
        sender = new Sender(sslSocket);

    }

    public void send(Package aPackage) throws Exception {
        sender.sendPackage(aPackage);
    }

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(5 * 1024);
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this);
        }
        return baos.toByteArray();
    }

    public static Client deserialize(byte[] data) throws Exception {
        Client client;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            client = (Client) ois.readObject();
        }
        return client;
    }

    //не тестили
    public Package getPackageResp() {
        if (arrayDequeResp.isEmpty()) return null;

        Package pack = arrayDequeResp.getFirst();
        arrayDequeResp.removeFirst();
        return pack;
    }

    //не тестили
    public Package getPackageReq() {
        if (arrayDequeReq.isEmpty()) return null;

        Package pack = arrayDequeReq.getFirst();
        arrayDequeReq.removeFirst();
        return pack;
    }

    private static SSLSocketFactory getSocketFactory() throws Exception {
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
