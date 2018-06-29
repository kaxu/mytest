package com.kaxudodo.study;

/**
 * Created by aaron on 2018/5/25.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Severss {
    public static void main(String[] args) throws IOException {
        Random r = new Random();
        int a = r.nextInt(101);

        ServerSocket ss = new ServerSocket(8888);

        Socket s = ss.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String num ;
        while((num = br.readLine()) != null ){
            int i = Integer.valueOf(num);
            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            if (a < i) {
                pw.println("小了");
            }
            else if (a == i) {
                pw.println("YOU ARE WIN");

            } else if (a > i) {
                pw.println("大了");
            }
        }



//        s.close();
    }
}
