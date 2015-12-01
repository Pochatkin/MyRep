package com.example.julia.uley.manager;

import com.example.julia.uley.common.Package;

/**
 * Created by Михаил on 27.11.2015.
 */
public class ConnectManager {
    private Package workPackage;

    public ConnectManager(Package workPackage){
        this.workPackage = workPackage;
        start();
    }

    public Package getWorkPackage() {
        return workPackage;
    }

    private void start(){

    }
}
