package com.zbl.wwj.concurrent.step2.p73;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 11:02
 * @Description:
 * @Version: $
 */
public class AppServer extends Thread {

    private int port;

    private static final int DEFAULT_PORT = 12722;

    private boolean start = true;

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    private ServerSocket server;

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
            while (start) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);
                clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.dispose();
        }
    }

    public void  dispose() {
        System.out.println("dispose");
        this.clientHandlers.forEach(ClientHandler::stop);
        this.executor.shutdown();
    }

    public void shutdown() {
        this.start = false;
        this.interrupt();
        try {
            this.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
