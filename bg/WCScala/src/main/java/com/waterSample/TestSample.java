package com.waterSample;

import java.util.Random;

public class TestSample {

    public static void main(String[] args) {

        Random random = new Random();

        int[] R = new int[]{1,1,1,1,1,1,4,5,6,7,8,9,0,6,6,6,6,4,4,4,4,9,9,9,3,5,6,7,8,10};


//        for (int i = 0; i < R.length; i++) {
//            R[i] = random.nextInt(10000);
//        }

        int k = 8;
        int[] K = new int[k];

        for (int f = 0; f < k; f++) {
            K[f] = R[f];
        }

        int j;

        for (int i = k ;i<R.length ; i++){
            j = random.nextInt(i);
            if (j < k) K[j] = R[i];
        }

        for (int i = 0; i < K.length; i++) {
            System.out.print(K[i] + "\t");
        }

    }
}
