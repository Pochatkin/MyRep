package com.example.julia.uley.client;

import com.example.julia.uley.common.Login;
import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;
import com.example.julia.uley.common.Pass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Scanner;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Сергей on 20.11.2015.
 */
public class Client {
    private static File aFileKeyStore = new File("C:/Android/Uley/uley/src/main/res/client_key_store.jks");
    private static File aFileServer = new File("C:/Android/Uley/uley/src/main/res/server.crt");
    private static String tsName = aFileKeyStore.getName();
    private static String ServerCrtName = aFileServer.getName();

    private static final int TIMEOUT = 500;
    private static final int PORT = 3128;

    private Sender sender;
    private Listener listener;

    private SSLSocketFactory socketFactory;
    private SSLSocket sslSocket;

    public Client() throws Exception {
        socketFactory = getSocketFactory();
        sslSocket = (SSLSocket) socketFactory.createSocket("10.8.30.224", PORT);
        sslSocket.setSoTimeout(TIMEOUT); //ждем ответа TIMEOUT миллисек

        sender = new Sender(sslSocket);
        listener = new Listener(sslSocket);
    }

    public void start(Package sendPackage) throws Exception {

        //В отдельном потоке принимаем пакеты
        Thread t = new Thread(new Runnable() {
            public void run() {
                listener.start();
            }
        });
        t.start();

        //Отправляем пакет
        sender.sendPackage(sendPackage);

    }

    private Package stringToPackage(String str) throws Exception {

        Scanner scanner = new Scanner(str).useDelimiter("; ");

        String command = scanner.next();
        String login = scanner.next();
        String passOrMessage = scanner.next();


        switch (command) {
            case "sign in":
                return new Package(PackageType.REQ_SIGN_IN, new Login(login), new Pass(passOrMessage));
            case "sign up":
                return new Package(PackageType.REQ_SIGN_UP, new Login(login), new Pass(passOrMessage));
            case "sign out":
                return new Package(PackageType.REQ_SIGN_OUT);
            case "send":
                return new Package(passOrMessage, new Login(login));
            default:
                throw new Exception();
        }
    }

    private static SSLSocketFactory getSocketFactory() throws Exception {
        KeyStore ks = KeyStore.getInstance("BKS");
        try {
            FileInputStream trustStream = new FileInputStream(tsName);
            ks.load(trustStream, null);
        } catch (FileNotFoundException e) {
            CertificateFactory crtFactory = CertificateFactory.getInstance("X.509");
            Certificate cert = crtFactory.generateCertificate(new FileInputStream(ServerCrtName));
            ks.load(null, null);
            ks.setCertificateEntry("server_cert", cert);
            FileOutputStream fos = new FileOutputStream(tsName);
            ks.store(fos, "123456".toCharArray());
            fos.close();
        }

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ks);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManager[] trustManagers = tmf.getTrustManagers();
        sslContext.init(null, trustManagers, null);
        return sslContext.getSocketFactory();
    }

//    public static void main(String[] args) {
//        try {
//            Client client = new Client();
//            client.start();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
}
