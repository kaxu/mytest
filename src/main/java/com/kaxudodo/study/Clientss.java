package com.kaxudodo.study;

/**
 * Created by aaron on 2018/5/25.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clientss {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8888);

        int count=0;
        while (count<7) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入数据(0-100 包括0,包括100):");
            int num = br.read();

            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            pw.println(num);

            BufferedReader brr = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line = brr.readLine();
            System.out.println(line);
            if(line.equals("YOU ARE WIN")){
                break;
            }
            count++;
        }

        s.close();

    }
}