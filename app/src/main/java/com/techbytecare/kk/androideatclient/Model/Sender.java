package com.techbytecare.kk.androideatclient.Model;

/**
 * Created by kundan on 12/21/2017.
 */

public class Sender {

    public String to;
    public Notification notification;


    public Sender(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
