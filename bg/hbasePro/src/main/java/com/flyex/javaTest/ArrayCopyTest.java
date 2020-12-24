package com.flyex.javaTest;

import java.util.ArrayList;
import java.util.Arrays;


public class ArrayCopyTest {

    public static void printAyy(int[] arr){
        for (int i = 0; i< arr.length; i++){
            System.out.print(arr[i]+ " ");
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[4];

        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;

        int mid = arr.length/2;


        printAyy(Arrays.copyOfRange(arr,0,mid));
        System.out.println("");
        printAyy(Arrays.copyOfRange(arr,mid,arr.length));
    }
}
