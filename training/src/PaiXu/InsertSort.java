package PaiXu;

public class InsertSort {
    //简单选择排序 （1）基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；然后在剩下的数当中再找最小的与第二个
    // 位置的数交换，如此循环到倒数第二个数和最后一 个数比较为止。
    public static void sort(int a[]){
        for (int i=0;i<a.length;i++){
            for (int j=i+1;j<a.length;j++){
                int temp = a[i];
                if (a[j]<a[i]){
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+"  ");
        }
        System.out.println();
    }
    //冒泡排序 在选取的比较区内大的往上冒
    public static void bubbleSort(int a[]){
        for (int i=0;i<a.length;i++){
            for (int j=i;j<a.length-1;j++){
                if(a[j]>a[j+1]){
                    int temp=a[j];
                    a[j] = a[j+1];
                    a[j+1] =temp;
                }
            }
        }
        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+"  ");
        }
        System.out.println();
    }




    public static void main(String[] args) {
        int a[] = {3,5,7,9,2,8,60,1,61,55};
        int temp=0;
        for(int i=1;i<a.length;i++){
            int j=i-1;
            temp=a[i];
            /*
            直接插入排序 基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排
            好顺序的，现在要把第n 个数插到前面的有序数中，使得这 n个数也是排好顺序的。如
            此反复循环，直到全部排好顺序。
             */
            /**1、for(;j>=0&&temp<a[j];j--){
                a[j+1]=a[j];  //将大于temp 的值整体后移一个单位
                }
            a[j+1]=temp;**/
            while (j>=0){
                if (a[j]>temp){
                    a[j+1] = a[j];
                    a[j]=temp;
                }
                j--;
            }
        }

        for (int i=0;i<a.length;i++){
            //System.out.print(a[i]+"  ");
        }
        int aa[] = {1,2,0,6,7,5,88,77,78};
        sort(aa);
        bubbleSort(aa);
    }
}
