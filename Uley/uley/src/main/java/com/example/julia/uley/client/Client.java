package com.example.julia.uley.client;

import com.example.julia.uley.common.Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    private static String tsName = "src/res/client_key_store.jks";
    private static String ServerCrtName = "src/res/server.crt";
    private static final int TIMEOUT = 500;
    private static final int PORT = 3128;

    private ArrayDeque<Package> arrayDeque;

    private Sender sender;

    private SSLSocketFactory socketFactory;
    private SSLSocket sslSocket;

    public Client() throws Exception {

        socketFactory = getSocketFactory();
        sslSocket = (SSLSocket) socketFactory.createSocket("localhost", PORT);
        sslSocket.setSoTimeout(TIMEOUT); //ждем ответа TIMEOUT миллисек

        arrayDeque = new ArrayDeque<Package>();
        sender = new Sender(sslSocket);

        //В отдельном потоке принимаем пакеты
        new Listener(sslSocket, arrayDeque);
    }

    public void send(Package aPackage) throws Exception {
        sender.sendPackage(aPackage);
    }

    public Package getPackage() {
        if(arrayDeque.isEmpty()) return null;

        Package pack =  arrayDeque.getFirst();
        arrayDeque.removeFirst();
        return  pack;
    }

//    private Package stringToPackage(String str) throws Exception {
//
//        Scanner scanner = new Scanner(str).useDelimiter("; ");
//
//        String command = scanner.next();
//        String login = scanner.next();
//        String passOrMessage = scanner.next();
//
//
//        switch (command) {
//            case "sign in":
//                return new Package(PackageType.REQ_SIGN_IN, new Login(login), new Pass(passOrMessage));
//            case "sign up":
//                return new Package(PackageType.REQ_SIGN_UP, new Login(login), new Pass(passOrMessage));
//            case "sign out":
//                return new Package(PackageType.REQ_SIGN_OUT);
//            case "send":
//                return new Package(passOrMessage, new Login(login));
//            default:
//                throw new Exception();
//        }
//    }

    private static SSLSocketFactory getSocketFactory() throws Exception {
        KeyStore ks = KeyStore.getInstance("JKS");
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

    public static void main(String[] args) {
        try {
            Client client = new Client();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
