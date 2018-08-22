package com.kaxudodo.leetcode;

/**
 * Created by aaron on 2018/8/22.
 */
public class NumArray {

    private final int[] nums ;

    private int chunkSize = 50;

    private int[] chunkSum;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.initChunkSum();
    }

    private void initChunkSum(){

        if(nums.length>chunkSize){
            chunkSum = new int[nums.length/chunkSize];
            int leftNum = nums.length;
            int i = 0;
            int j = 0;
            while (leftNum >= chunkSize){
                chunkSum[j] = sumRange(i,i+chunkSize-1);
                j++;
                i+=chunkSize;
                leftNum-=chunkSize;
            }
        }

    }

    public int sumRange(int i, int j) {
        int result = 0;

        int length = j-i;
        if(length>=2*chunkSize){
            int[] indexs = getAddIndex(i,j);
            if(indexs[0] != -1){
                result += sumRange(indexs[0],indexs[1]);
            }
            result += getChunkSum(indexs[2],indexs[3]);
            result += sumRange(indexs[4],indexs[5]);
            return result;
        }

        while (i <= j){
            result += nums[i];
            i++;
        }
        return result;
    }

    private int[] getAddIndex(int i,int j){
        int[] indexs = new int[6];

        int mod = i%chunkSize;
        if(mod == 0){
            int index = i/chunkSize;
            int indexEnd = j/chunkSize;
            int end = indexEnd*chunkSize;
            indexs = new int[]{-1, -1, index, indexEnd - 1, end, j};
        }else {
            int index = i/chunkSize+1;
            int begin1 = index*chunkSize-1;
            int indexEnd = j/chunkSize;
            int end = indexEnd*chunkSize;
            indexs = new int[]{i, begin1, index, indexEnd - 1, end, j};
        }
        return indexs;
    }

    private int getChunkSum(int i,int j){
        if(i == j){
            return chunkSum[i];
        }else{
            int result = 0 ;
            for (int k = i; k <= j ; k++) {
                result+=chunkSum[k];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{6,5,4,-6,-7,-2,-9,-1,1,-6,-6,3,-6,-1,-7,-4,6,3,-1,-8,2,6,-7,3,-9,-4,-4,2,-2,-4,7,9,1,0,-8,-2,0,6,9,-7,-1,1,7,4,5,6,3,4,9,3,8,8,-7,-7,-7,3,-6,1,-4,-1,5,2,-5,2,-2,-8,7,-1,-9,6,5,-1,0,-6,5,-5,9,-3,5,4,1,9,-9,1,-1,-4,9,-6,-5,-5,9,6,-4,9,3,1,-6,7,9,-2,8,4,-3,-6,0,4,3,-6,4,3,-8,5,7,-5,-8,9,-7,1,6,7,1,-8,7,-8,0,1,-3,-1,-1,-5,5,1,-9,0,7,1,0,-3,-1,-7,8,-1,-6,1,-1,-2,-5,9,1,-9,3,-1,-2,-7,9,5,-7,9,6,4,-8,-6,4,-7,6,-1,-3,3,8,5,-9,4,-7,-6,9,-5,7,-5,-4,-4,-2,-2,-6,-7,1,3,4,0,9,7,8,0,6,5,2,-7,-2,4,9,0,8,4,1,-7,-3,2,-8,2,0,-8,3,3,2,-2,5,2,-2,4,7,4,5,-6,4,1,-4,-8,3,6,2,-2,2,-7,6,-4,5,-7,8,8,0,2,-2,4,-7,-3,0,-3,8,-3,9,5,0,2,-3,1,-6,2,5,-1,6,4,7,-6,-8,-3,4,5,-8,-5,-9,-7,4,-9,6,7,2,1,8,-1,3,-6,-4,-6,2,1,7,-5,-4,3,4,9,-2,-3,2,6,-1,-6,-3,-7,3,-1,-5,5,-7,8,7,-6,1,1,-5,-2,0,-7,7,-3,5,-9,-3,-3,-2,-8,9,-4,4,-6,2,4,1,-8,-8,-3,8,3,-1,-3,9,0,2,4,-2,-6,-2,5,-5,-6,5,-7,1,4,-2,5,5,-8,-3,2,6,5,-6,-2,9,6,-9,7,4,9,7,-3,6,2,1,0,-2,6,-5,-6,-3,-3,-3,1,3,-7,9,-5,-9,-2,-3,-7,2,-6,0,-3,9,3,-2,-6,3,-6,3,8,-3,3,-6,7,-6,4,7,-5,3,-1,-6,-8,-4,-2,-4};
        System.out.println(nums.length);
        NumArray na = new NumArray(nums);
        int result = na.sumRange(55,404);
        System.out.println(result);
//        413
//        61



    }
}
