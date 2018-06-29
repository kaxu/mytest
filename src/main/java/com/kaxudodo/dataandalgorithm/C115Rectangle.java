package com.kaxudodo.dataandalgorithm;


/**
 * Created by aaron on 16/8/10.
 * 一个Rectangle类
 */
public class C115Rectangle {

    private int length;

    private int width;

    public C115Rectangle(int length,int width){
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getS(){
        return getLength()*getWidth();
    }

    public int getC(){
        return getLength()+getWidth();
    }


    public static void main(String[] args) {
        C115Rectangle c115Rectangle = new C115Rectangle(1,2);
        C115Rectangle c115Rectangle2 = new C115Rectangle(3,2);
        C115Rectangle c115Rectangle3 = new C115Rectangle(4,8);
        C115Rectangle c115Rectangle4 = new C115Rectangle(5,6);
        C115Rectangle c115Rectangle5 = new C115Rectangle(6,5);
        C115Rectangle c115Rectangle6 = new C115Rectangle(3,10);

        C115Rectangle[] rectangles = {c115Rectangle,c115Rectangle2,c115Rectangle3,c115Rectangle4,c115Rectangle5,c115Rectangle6};
        int maxS = 0,maxC = 0;
        for (C115Rectangle rectangle:rectangles){
            if(rectangle.getS() >= maxS)
                maxS = rectangle.getS();
            if(rectangle.getC() >= maxC)
                maxC = rectangle.getC();
        }
        System.out.println("maxs "+maxS);
        System.out.println("maxc "+maxC);

    }

}
