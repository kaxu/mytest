package com.kaxudodo.dataandalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aaron on 16/8/8.
 */
public class C12 {

    public static void getWord(){
        char[][] a = new char[4][4];
        char[] r0 = new char[]{'t', 'h', 'i', 's'};
        char[] r1 = new char[]{'w', 'a', 't', 's'};
        char[] r2 = new char[]{'o', 'a', 'h', 'g'};
        char[] r3 = new char[]{'f', 'g', 'd', 't'};
        a[0] = r0;
        a[1] = r1;
        a[2] = r2;
        a[3] = r3;

//        String words = "this,is,";
        List<String> words = new ArrayList<>();
        words.add("this");
        words.add("si");

        //列
        String[] linef = new String[a[0].length];
        String[] linee = new String[a[0].length];

        //斜列
        int b = a.length > a[0].length ? a[0].length:a.length;
        char[][] incline = new char[a[0].length+a.length-1][b];
        int h = 0;
        for (int i = 0; i < a.length; i++) {
            char[] row = a[i];
            //从前往后拼接
            String rowf = "";
            //从后往前拼接
            String rowe = "";
            for (int j = 0; j < row.length; j++) {
                rowf = rowf+row[j];
                if(words.contains(rowf)){
                    System.out.println(rowf);
                }
                rowe = rowe + row[row.length-1-j];
                if(words.contains(rowe)){
                    System.out.println(rowe);
                }
                //列的拼接
                if(linef[j] == null) linef[j] = "";
                linef[j] = linef[j] + a[i][j];
                if(linee[j] == null) linee[j] = "";
                linee[j] = linee[j] + a[a.length-1-i][j];
                //倾斜的列
                if(i == 0 || j== 0){
                    incline[h][0] =  a[i][j];
                    h++;
                }else{
                    int index = 1;
                    int newi = i - 1;
                    int newj = j - 1;
                    boolean isOk = false;
                    while (!isOk){
                        if(newi == 0){
                            incline[j-index][index] = a[i][j];
                            isOk = true;
                        }else if(newj == 0){
                            incline[a[0].length+i-1-index][index] = a[i][j];
                            isOk = true;
                        }else {
                            newi--;
                            newj--;
                            index++;
                        }
                    }
                }

            }
        }
        System.out.println(Arrays.toString(linef));
        System.out.println(Arrays.toString(linee));
        for (int i = 0; i < incline.length; i++) {
            System.out.println(Arrays.toString(incline[i]));
        }
    }

    public static void main(String[] args) {
        getWord();
    }
}
