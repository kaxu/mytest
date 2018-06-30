package com.kaxudodo.dataandalgorithm;

import com.kaxudodo.datastructure.base.MyStack;
import com.kaxudodo.utils.RegexUtils;

import java.util.Arrays;

/**
 * Created by aaron on 16/8/19.
 */
public class C323 {

    public void getExpression(String[] ori){
        MyStack<String> stack = new MyStack<>();
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < ori.length; i++) {
            String e = ori[i];
            if(RegexUtils.isInteger(e))
                out.append(e);
            else if("(".equals(e)){
                stack.push(e);
            }else if(")".equals(e)){
                boolean goon = true;
                while (goon && stack.size() > 0){
                    String temp = stack.pop();
                    stack.push(temp);
                    if("(".equals(temp)){
                        stack.pop();
                        goon = false;
                        continue;
                    }else {
                        temp = stack.pop();
                        out.append(temp);
                    }
                }
            }else if("*".equals(e) || "/".equals(e)){
                boolean goon = true;
                while (goon && stack.size() > 0){
                    String temp = stack.pop();
                    stack.push(temp);
                    if("+".equals(temp) || "-".equals(temp) || "(".equals(temp)){
                        goon = false;
                        continue;
                    }else {
                        temp = stack.pop();
                        out.append(temp);
                    }
                }
                stack.push(e);
            }else if("+".equals(e) || "-".equals(e)){
                boolean goon = true;
                while (goon && stack.size() > 0){
                    String temp = stack.pop();
                    stack.push(temp);
                    if("(".equals(temp)){
                        goon = false;
                        continue;
                    }else {
                        temp = stack.pop();
                        out.append(temp);
                    }
                }
                stack.push(e);
            }
        }
        while (stack.size() > 0){
            out.append(stack.pop());
        }
        System.out.println(out.toString());
    }

    public static void main(String[] args) {
        String str = "1+2*3+(4*5+6)*7";
//        String str = "(3+(8*(3+2)+5))*6";
        String[] a = new String[str.length()];
        for (int i = 0; i < a.length; i++) {
            a[i] = str.substring(i,i+1);
        }
        System.out.println(Arrays.toString(a));
        C323 c323 = new C323();
        c323.getExpression(a);
    }
}
