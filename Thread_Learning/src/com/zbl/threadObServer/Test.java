package com.zbl.threadObServer;



import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        ThreadObServer listener = new ThreadObServer();
        Arrays.asList("1","2").stream().forEach(id ->{
            new Thread(new ObSserverRunnable(listener),id).start();
        });
    }
}
