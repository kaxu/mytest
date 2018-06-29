package com.kaxudodo.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by aaron on 17/5/17.
 */
public class SocketTest {


    final static String response =
            "HTTP/1.0 200 OKrn" +
                    "Content-type: text/plainrn" +
                    "rn" +
                    "Hello Worldrn";

    public static void handleRequest(Socket socket) throws IOException {
        // Read the input stream, and return "200 OK"
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

//            logger.info(in.readLine());
            System.out.println(in.readLine());
            OutputStream out = socket.getOutputStream();
            out.write(response.getBytes(StandardCharsets.UTF_8));
        } finally {
            socket.close();
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8080);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    handleRequest(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            listener.close();
        }
    }
}
