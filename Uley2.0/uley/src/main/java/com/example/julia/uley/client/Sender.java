package com.example.julia.uley.client;

import com.example.julia.uley.common.Package;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.net.ssl.SSLSocket;

/**
 * Created by Сергей on 04.12.2015.
 */
public class Sender implements Serializable {
    private transient OutputStream outputStream;

    public Sender(SSLSocket sslSocket) {
        try {
            outputStream = sslSocket.getOutputStream();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(5 * 1024);
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this);
        }
        return baos.toByteArray();
    }

    public static Sender deserialize(byte[] data) throws Exception {
        Sender sender;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            sender = (Sender) ois.readObject();
        }
        return sender;
    }

    public void sendPackage(Package aPackage) throws IOException {
        byte[] serialized = aPackage.serialize();
        System.out.println("length of package: " + serialized.length);
        outputStream.write(serialized, 0, serialized.length);
        outputStream.flush();
    }

}
