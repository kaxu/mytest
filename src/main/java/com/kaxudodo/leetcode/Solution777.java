package com.kaxudodo.leetcode;

import com.sun.media.sound.SoftTuning;

import java.util.Arrays;

public class Solution777 {



    public boolean canTransform(String start, String end) {
        boolean canTransform = true;
        if (start.equals(end))
            return canTransform;

        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
//        String[] starts = new String[start.length()];
        char[] starts = start.toCharArray();
        for (int i = 0; i < start.length(); i++) {
//            starts[i] = start.substring(i, i + 1);
            if (starts[i] == 'L') {
                a++;
            }
            if (starts[i] == 'X') {
                b++;
            }
            if (starts[i] == 'R') {
                c++;
            }
        }
        char[] ends = end.toCharArray();
        for (int i = 0; i < start.length(); i++) {
//            ends[i] = end.substring(i, i + 1);
            if (ends[i] == 'L') {
                d++;
            }
            if (ends[i] == 'X') {
                e++;
            }
            if (ends[i] == 'R') {
                f++;
            }
        }
        if (a != d || b != e || c != f)
            return false;

        return isOver(starts,ends);
    }

    public final boolean isOver(char[] starts,char[] ends){
        for (int i = 0; i < starts.length; i++) {
            if(starts[i] == (ends[i]))
                continue;
            if('L' == (starts[i]) && 'R' == (ends[i]))
                return false;
            if('R' == (starts[i]) && 'L' == (ends[i]))
                return false;
            if('X' == (starts[i]) && 'L' == (ends[i])){
                int j = i+1;
                boolean change = false;
                while(j<starts.length){
                    if('X' == (starts[j])){
                        j++;
                        continue;
                    }
                    if('R' == (starts[j]))
                        return false;
                    if('L' == (starts[j])){
                        starts[i]='L';
                        starts[j]='X';
                        change = true;
                        break;
                    }
                }
                if(!change)
                    return false;
                continue;
            }
            if('X' == (starts[i]) && 'R' == (ends[i])){
                return false;
            }
            if('L' == (starts[i]) && 'X' == (ends[i])){
                return false;
            }
            if('R' == (starts[i]) && 'X' == (ends[i])){
                int j = i+1;
                boolean change = false;
                while(j<starts.length){

                    if('R' == (starts[j])){
                        j++;
                        continue;
                    }
                    if('L' == (starts[j]))
                        return false;
                    if('X' == (starts[j])){
                        starts[i]='X';
                        starts[j]='R';
                        change=true;
                        break;
                    }
                }
                if(!change)
                    return false;
                continue;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        Solution777 solution777 = new Solution777();
        boolean b = solution777.canTransform("RL","LR");
        System.out.println(b);
//        char a = 'L';
//        System.out.println(a == 'l');
    }

}
