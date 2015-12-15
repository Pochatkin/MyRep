package com.example.julia.uley.manager;

import com.example.julia.uley.client.Client;
import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;

/**
 * Created by Михаил on 09.12.2015.
 */
public class ListenerREQManager {
    private Client client;
    private com.example.julia.uley.common.Package tempPackage;

    public ListenerREQManager(Client client) {
        this.client = client;
    }

    private void setTempPackage() {
        //TODO: CHECK
        tempPackage = client.getPackageReq();
    }

    public Package listen() {
        setTempPackage();
        System.out.println("Start listening");
        while (true) {
            if (tempPackage == null) {
                setTempPackage();
                continue;
            }
            if (tempPackage.getType() == PackageType.REQ_SEND_MESSAGE) {
                System.out.println("Get REQ");
                return tempPackage;
            }
        }
    }
}
