package com;


import java.util.ArrayList;

public class HeapSort {

    public static void sortDown(int[] arr){

        if (arr.length == 1){
            return;
        }

        toHeapStatus(arr);



    }

    private static void toHeapStatus(int[] arr) {
        int leafNodeIndex = arr.length/2-1;
        for (int i = leafNodeIndex; i >=0 ; i--) {
            sort(arr,i);
        }
    }

    private static boolean isHaveTwoSons(int[] arr,int leafNodeIndex){
        if (2*leafNodeIndex+2 > arr.length-1){
            return false;
        }
        return true;
    }
    private static boolean isHaveSon(int[] arr,int leafNodeIndex){
        if (2*leafNodeIndex+1 > arr.length){
            return false;
        }
        return true;
    }

    private static boolean isNeedSort(int[] arr,int leafNodeIndex) {
        int leftSon = 2*leafNodeIndex+1;
        int rightSon;
        if (isHaveTwoSons(arr,leafNodeIndex)){
            rightSon = 2*leafNodeIndex+2;
            if (arr[leafNodeIndex] > arr[leftSon] && arr[leafNodeIndex] > arr[rightSon]){
                return false;
            }
            return true;
        }else {
            if (arr[leafNodeIndex] > arr[leftSon]){
                return false;
            }
            return true;
        }

    }

    public static void sort(int[] arr,int leafNodeIndex){

//        if (leafNodeIndex == arr.length/2-1){
//            if (isNeedSort(arr,leafNodeIndex)){
//                sort(arr,leafNodeIndex);
//                return;
//            }
//        }

        if (isNeedSort(arr,leafNodeIndex)){
            if (isHaveTwoSons(arr,leafNodeIndex)){
                int leftSon = 2*leafNodeIndex+1;
                int rightSon = 2*leafNodeIndex+2;
                if (arr[leftSon] > arr[rightSon]) {
                    int temp = arr[leafNodeIndex];
                    arr[leafNodeIndex] = arr[leftSon];
                    arr[leftSon] = temp;
                    if (isNeedSort(arr,leftSon)){
                        if (isHaveSon(arr,leftSon)){
                            sort(arr,leftSon);
                        }
                        return;
                    }
                }else {
                    int temp = arr[leafNodeIndex];
                    arr[leafNodeIndex] = arr[rightSon];
                    arr[rightSon] = temp;
                    if (isNeedSort(arr,leftSon)){
                        if (isHaveSon(arr,leftSon)){
                            sort(arr,leftSon);
                        }
                        return;
                    }
                }
            }else {
                int leftSon = 2*leafNodeIndex+1;
                int temp = arr[leafNodeIndex];
                arr[leafNodeIndex] = arr[leftSon];
                arr[leftSon] = temp;
                if (isNeedSort(arr,leftSon)){
                    if (isHaveSon(arr,leftSon)){
                        sort(arr,leftSon);
                    }
                    return;
                }
            }
        }



    }


}
