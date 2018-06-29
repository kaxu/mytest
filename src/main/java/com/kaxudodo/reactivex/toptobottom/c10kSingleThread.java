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
public class c10kSingleThread {

    public static final byte[] RESPONSE = ("HTTP/1.1 200 OK\r\n" + "Content-length: 2\r\n" + "\r\n" + "OK").getBytes();

    private static void handle(Socket client) {
        log(client);
        try {
            while (!Thread.currentThread().isInterrupted()) {
                readFullRequest(client);
                client.getOutputStream().write(RESPONSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            IOUtils.closeQuietly(client);
        }

    }

    private static void readFullRequest(Socket client) throws IOException {
        BufferedReader reader = new BufferedReader(

                new InputStreamReader(client.getInputStream()));
        String line = reader.readLine();
        while (line != null && !line.isEmpty()) {

            line = reader.readLine();
        }

    }

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8080, 100);
        while (!Thread.currentThread().isInterrupted()) {
            final Socket client = serverSocket.accept();
            log(client);
            handle(client);
        }
    }
}
