package com.kaxudodo.dataandalgorithm;

import com.kaxudodo.staticclass.MyStack;

/**
 * Created by aaron on 16/8/19.
 */
public class C322 {

    public void calculate(String[] expression){
        MyStack<String> stack = new MyStack<>();

        int temp = 0;
        for (int i = 0; i < expression.length; i++) {
            String e = expression[i];
            if("+".equals(e)){
                long a = Long.parseLong(stack.pop());
                long b = Long.parseLong(stack.pop());
                long c = a + b;
                stack.push(String.valueOf(c));
            } else if("*".equals(e)){
                long a = Long.parseLong(stack.pop());
                long b = Long.parseLong(stack.pop());
                long c = a * b;
                stack.push(String.valueOf(c));
            }else{
                if(!e.isEmpty())
                    stack.push(e);
            }
        }
        System.out.println(stack.pop());
    }

    public void getMidExpress(String[] expression){
        MyStack<String> stack = new MyStack<>();

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < expression.length; i++) {
            String e = expression[i];
            if("+".equals(e)){
                String a = stack.pop();
                String b = stack.pop();
//                long c = a + b;
//                buffer.append(a+"+"+b);
//                stack.push(String.valueOf(c));
                stack.push(String.valueOf("("+a+"+"+b+")"));
            } else if("*".equals(e)){
                String a = stack.pop();
                String b = stack.pop();
//                long c = a * b;
//                buffer.append(a+"*"+b);
//                stack.push(String.valueOf(c));
                stack.push(String.valueOf(a+"*"+b));
            }else{
                if(!e.isEmpty())
                    stack.push(e);
            }
        }
        System.out.println(stack.pop());
    }

    public static void main(String[] args) {
        String str = "123*+45*6+7*+";
        String[] a = new String[str.length()];
        for (int i = 0; i < a.length; i++) {
            a[i] = str.substring(i,i+1);
        }
        C322 c322 = new C322();
        c322.getMidExpress(a);
    }
}
