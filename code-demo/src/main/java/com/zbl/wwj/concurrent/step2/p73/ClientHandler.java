package com.zbl.wwj.concurrent.step2.p73;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 11:07
 * @Description:
 * @Version: $
 */
public class ClientHandler implements Runnable {

    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter pw = new PrintWriter(outputStream);
        ) {
            while (running) {
                String message = br.readLine();
                if (message == null) {
                    break;
                }
                System.out.println("Come from client > " + message);
                pw.write("echo " + message + "\n");
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.stop();
        }

    }

    public void stop() {
        if (!running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
            System.out.println("Client close " + Thread.currentThread().getName());
        } catch (IOException e) {
            System.out.println("close exp");
        }
    }
}
