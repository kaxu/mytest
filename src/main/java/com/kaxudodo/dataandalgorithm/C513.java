package com.kaxudodo.dataandalgorithm;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class C513 {

    private int numberp1;
    private int numberp2;

    private ArrayList<PP> p1list ;
    private ArrayList<PP> p2list ;

    public void initList(){
        numberp1 = 2;
        numberp2 = 4;
        p1list = new ArrayList<>(numberp1*2);
        p2list = new ArrayList<>(numberp2*2);
        addPP(numberp1,p1list);
        addPP(numberp2,p2list);
        System.out.println(Arrays.toString(p1list.toArray()));
        System.out.println(Arrays.toString(p2list.toArray()));
    }

    private void addPP(int number,ArrayList<PP> p){
        for (int i = 0; i < number; i++) {
            PP pp = new PP();
            int coefficient = RandomUtils.nextInt(1,10);
            int power = RandomUtils.nextInt(1,10);
            pp.setCoefficient(coefficient);
            pp.setPower(power);
            p.add(pp);
        }
    }


    public static void main(String[] args) {
        C513 c513 = new C513();
        c513.initList();
        Map<Integer,PP> map = new HashMap<>();
        for(PP p1:c513.getP1list()){
            for(PP p2:c513.getP2list()){
                int coefficient = p1.getCoefficient()*p2.getCoefficient();
                int power = p1.getPower()+p2.getPower();
                PP pp = new PP();
                pp.setPower(power);
                pp.setCoefficient(coefficient);
                // add to map
                PP mapp = map.get(pp.getPower());
                if(mapp == null){
                    map.put(pp.getPower(),pp);
                }else{
                    mapp.setCoefficient(mapp.getCoefficient()+pp.getCoefficient());
                }
            }

        }
        System.out.println(Arrays.toString(map.values().toArray()));

        //sort
        //利用map的kyes进行排序 这里省略
    }


    static class PP {
        private int  coefficient;
        private int power;

        public int getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(int coefficient) {
            this.coefficient = coefficient;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        @Override
        public String toString() {
            return "PP{" +
                    "coefficient=" + coefficient +
                    ", power=" + power +
                    '}';
        }
    }

    public ArrayList<PP> getP1list() {
        return p1list;
    }

    public ArrayList<PP> getP2list() {
        return p2list;
    }
}
