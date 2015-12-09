package com.example.julia.uley.manager;

import com.example.julia.uley.client.Client;
import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;

/**
 * Created by Михаил on 07.12.2015.
 */
public class ListenerManager {

    private Client client;
    private Package tempPackage;

    public ListenerManager(Client client) {
        this.client = client;
    }

    private void setTempPackage() {
        //TODO: CHECK
        tempPackage = client.getPackageResp();
    }

    public Package listenerManager() {
        setTempPackage();
        while (true) {
            if (tempPackage == null){
                setTempPackage();
                continue;
            }
            if (tempPackage.getType() == PackageType.RESP_SIGN_IN_OK) {
                return tempPackage;
            }
            if(tempPackage.getType() == PackageType.RESP_SIGN_IN_FAILED){
                return tempPackage;
            }
            if (tempPackage.getType() == PackageType.RESP_MESSAGE_USER_NOT_FOUND) {
                return tempPackage;
            }
            if(tempPackage.getType() == PackageType.RESP_MESSAGE_DELIVERED){
                return tempPackage;
            }
            if(tempPackage.getType() == PackageType.RESP_MESSAGE_IN_QUEUE){
                return tempPackage;
            }
            if(tempPackage.getType() == PackageType.RESP_SIGN_OUT_OK){
                return tempPackage;
            }
            if(tempPackage.getType() == PackageType.RESP_SIGN_UP_LOGIN_FILTER_FAILED){
                return tempPackage;
            }
            if (tempPackage.getType() == PackageType.RESP_SIGN_UP_OK) {
                return tempPackage;
            }
            if(tempPackage.getType() == PackageType.RESP_SIGN_UP_PASS_FILTER_FAILED){
                return tempPackage;
            }
            if(tempPackage.getType() == PackageType.RESP_SIGN_UP_USER_ALREADY_EXIST){
                return tempPackage;
            }
            if (tempPackage.getType() == PackageType.RESP_SEARCH_ANSWER) {
                return tempPackage;
            }
            if(tempPackage.getType() == PackageType.RESP_SERVER_ERROR){
                return tempPackage;
            }
            if(tempPackage.getType() == PackageType.REQ_SEND_MESSAGE){
                return tempPackage;
            }
        }
    }
}
