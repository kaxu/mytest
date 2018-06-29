package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/7/31.
 */
public class C16 {

    //交换两个字符
    void swap(char[] str ,int a,int b)
    {
//        System.out.println(a+"------"+b);
        char temp = str[a];
        str[a] = str[b];
        str[b] = temp;
    }

    //递归全排列，start 为全排列开始的下标， length 为str数组的长度
    void allRange(char[] str,int start,int length)
    {
        if(start == length-1)
        {
            System.out.println(str);
        }
        else
        {
            for(int i=start;i<=length-1;i++)
            {	//从下标为start的数开始，分别与它后面的数字交换
                swap(str,start,i);
//                System.out.println(i);
                allRange(str,start+1,length);
                swap(str,start,i);
            }
        }
    }

    public static void main(String[] args) {
        C16 permutate = new C16();
        permutate.allRange(new char[]{'a', 'b', 'c'},0,3);
    }
}
