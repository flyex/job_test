package find;

public class Erfenfa {

    public static int find(int[] arr, int value){

        int low = 0;
        int high = arr.length-1;

        while(low <= high){
            int mid = (low + high) >> 1;
            if (value == arr[mid]){
                return mid;
            }else if (value < arr[mid]){
                high = mid;
            } else {
                low = mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,7,9,11,14,15,18,20};
        int value = 11;
        System.out.println(find(arr,value));
    }
}
