package com.kaxudodo.leetcode;

import com.sun.media.sound.SoftTuning;

import java.util.Arrays;

public class Solution777 {

    public boolean canTransform(String start, String end) {
        boolean canTransform = true;
        if(start.equals(end))
            return canTransform;

        int a = 0,b = 0,c = 0,d = 0,e = 0,f = 0;
        String[] starts = new String[start.length()];

        for (int i = 0; i < start.length(); i++) {
            starts[i]=start.substring(i,i+1);
            if(starts[i].equals("L")){a++;}
            if(starts[i].equals("X")){b++;}
            if(starts[i].equals("R")){c++;}
        }
        String[] ends = new String[end.length()];
        for (int i = 0; i < start.length(); i++) {
            ends[i]=end.substring(i,i+1);
            if(ends[i].equals("L")){d++;}
            if(ends[i].equals("X")){e++;}
            if(ends[i].equals("R")){f++;}
        }
        if(a != d || b != e || c != f)
            return false;


        String x = "";
        int beginIndex = 0;
//        int endIndex = 0;
        for (int i = 0; i < start.length(); i++) {

            if("".equals(x)){
                x = starts[i];
                continue;
            }

            if("X".equals(x) && !"R".equals(starts[i])){
                x = starts[i];
                continue;
            }
            if("X".equals(x) && "R".equals(starts[i]) && !"R".equals(starts[beginIndex])){
                x = starts[i];
                boolean isOk = isOk(beginIndex,i-1,starts,ends);
                if(!isOk)
                    return false;
                beginIndex = i;
                continue;
            }
            if("X".equals(x) && "R".equals(starts[i]) && "R".equals(starts[beginIndex])){
                x = starts[i];
                continue;
            }
            if("L".equals(x) && "L".equals(starts[i])){
                x = starts[i];
                continue;
            }
            if("L".equals(x) && !"L".equals(starts[i])){
                x = starts[i];
                boolean isOk = isOk(beginIndex,i-1,starts,ends);
                if(!isOk)
                    return false;
                beginIndex = i;
                continue;
            }
            if("R".equals(x) && !"L".equals(starts[i])){
                x = starts[i];
                continue;
            }
            if("R".equals(x) && "L".equals(starts[i])){
                x = starts[i];
                boolean isOk = isOk(beginIndex,i-1,starts,ends);
                if(!isOk)
                    return false;
                beginIndex = i;
                continue;
            }
        }
        boolean isOk = isOk(beginIndex,starts.length-1,starts,ends);
        if(!isOk)
            return false;


//        changeL(starts);changeR(starts);
//        changeL(ends);changeR(ends);
//        for (int i = 0; i < starts.length; i++) {
//            if(!starts[i].equals(ends[i])){
//                canTransform = false;
//                break;
//            }
//        }
//        StringBuffer sb = new StringBuffer();
//        for(int i = 0; i < starts.length; i++){
//            sb. append(starts[i]);
//        }
//        String newstart  = sb.toString();
//        newstart = newstart.replaceAll("X*L","L");
//        newstart = newstart.replaceAll("RX*","R");
//        String newend = end.replaceAll("X*L","L");
//        newend = end.replaceAll("RX*","R");
//        if(newstart.equals(newend))
//            canTransform=true;

        return canTransform;
    }

    public boolean isOk(int begin,int end,String[] starts,String[] ends){
        String[] chunkstart = new String[end-begin+1];
        String[] chunkend = new String[end-begin+1];
        int h = 0;
        for (int i = begin; i <= end ; i++) {
            chunkstart[h]=starts[i];
            chunkend[h]=ends[i];
            h++;
        }
        boolean canTransform = true;
//        changeL(chunkstart);changeR(chunkstart);
//        changeL(chunkend);changeR(chunkend);
        String[] chunkstartnox = new String[end-begin+1];
        String[] chunkendnox = new String[end-begin+1];
        int y=0,z=0;
        for (int i = 0; i < chunkstart.length; i++) {
            if(!"X".equals(chunkstart[i])){
                chunkstartnox[y]=chunkstart[i];
                y++;
            }
            if(!"X".equals(chunkend[i])){
                chunkendnox[z]=chunkend[i];
                z++;
            }
        }
        for (int i = 0; i < chunkstart.length; i++) {
            if(chunkstartnox[i] == null && chunkendnox[i] != null){canTransform = false;break;}
            if(chunkstartnox[i] != null && chunkendnox[i] == null){canTransform = false;break;}
            if(chunkstartnox[i] == null && chunkendnox[i] == null){continue;}
            if(!chunkstartnox[i].equals(chunkendnox[i])){
                canTransform = false;
                break;
            }
        }

        return canTransform;
    }

    public void changeR(String[] x){
        for (int i = 0; i < x.length; i++) {
            if(i != x.length-1){
                int j = i+1;
                while(j<x.length && "R".equals(x[i]) && !"L".equals(x[j])){
                    if("X".equals(x[j])){
                        x[i]="X";
                        x[j]="R";
                        break;
                    }
                    j++;
                }
            }
        }
    }

    public void changeL(String[] x){
        for (int i = x.length-1; i >=0; i--) {
            if(i != 0){
                int j = i-1;
                while(j>=0 && "L".equals(x[i]) &&  !"R".equals(x[j])){
                    if("X".equals(x[j])){
                        x[i]="X";
                        x[j]="L";
                        break;
                    }
                    j--;
                }
            }

        }
    }

    public static void main(String[] args) {
        Solution777 solution777 = new Solution777();
        boolean b = solution777.canTransform("XXRXXLXLXLXXRXXLXLLX","XXXRLLLXXXXXXXRLLLXX");
        System.out.println(b);
    }

}
