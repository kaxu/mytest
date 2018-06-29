package com.kaxudodo.reactivex.toptobottom;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static com.kaxudodo.reactivex.ReactiveUtil.log;

/**
 * Created by aaron on 2018/6/28.
 */
public class c10kThreadPerConnection extends HttpServer {

    public static void main(String[] args) throws IOException {
        new c10kThreadPerConnection().run(8080);
    }

    @Override
    void handle(ClientConnection clientConnection) {
        log(clientConnection);
        new Thread(clientConnection).start();
    }
}

class SingleThread extends HttpServer {

    public static void main(String[] args) throws Exception {

        new SingleThread().run(8080);
    }

    @Override
    void handle(ClientConnection clientConnection) {
        log(clientConnection);
        clientConnection.run();
    }

}

abstract class HttpServer {
    void run(int port) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port, 100);
        while (!Thread.currentThread().isInterrupted()) {
            final Socket client = serverSocket.accept();
            log("accept "+client);
            handle(new ClientConnection(client));
        }
    }

    abstract void handle(ClientConnection clientConnection);

}

class ClientConnection implements Runnable {
    public static final byte[] RESPONSE = ("HTTP/1.1 200 OK\r\n" + "Content-length: 2\r\n" + "\r\n" + "OK").getBytes();

    public static final byte[] SERVICE_UNAVAILABLE = ("HTTP/1.1 503 Service unavailable\r\n").getBytes();

    private final Socket client;

    ClientConnection(Socket client) {

        this.client = client;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                readFullRequest();
                log("readFullRequest");
                client.getOutputStream().write(RESPONSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            IOUtils.closeQuietly(client);
        }

    }

    private void readFullRequest() throws IOException {
        BufferedReader reader = new BufferedReader(

                new InputStreamReader(client.getInputStream()));
        String line = reader.readLine();
        while (line != null && !line.isEmpty()) {

            line = reader.readLine();
        }
    }

    public void serviceUnavailable() {
        try {

            client.getOutputStream().write(SERVICE_UNAVAILABLE);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

}