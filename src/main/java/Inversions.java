import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Inversions {
    private static int getNumberOfInversions(int[]a, int[]b,int left, int right){
        int mid, numberOfInversions = 0;
        if(right > left){
            mid = (right + left)/2;
            numberOfInversions = getNumberOfInversions(a,b,left,mid);
            numberOfInversions += getNumberOfInversions(a,b,mid+1,right);
            numberOfInversions += merge(a,b,left,mid+1,right);
        }
        return numberOfInversions;
    }
    static int merge(int[] arr, int[] tem, int left, int mid, int right){
        int numberOfInversions = 0, i = left, j = mid, k = left;
        while((i <= mid-1)&&(j <= right)){
            if(arr[i] <= arr[j]){
                tem[k++] = arr[i++];
            }else{
                tem[k++] = arr[j++];
                numberOfInversions = numberOfInversions + (mid - i);
            }
        }
        while (i <= mid -1){
            tem[k++] = arr[i++];
        }
        while(j <= right){
            tem[k++] = arr[j++];
        }
        for(i =left;i<=right;i++){
            arr[i] = tem[i];
        }
        return numberOfInversions;
    }
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for(int i =0; i<n; i++){
            a[i] = scanner.nextInt();
        }
        int [] b= new int[n];
        System.out.println(getNumberOfInversions(a,b,0,a.length-1));

    }
    public static int naive(int[] a){
        int numberOfInversions = 0;
        if(a.length < 2){
            return numberOfInversions;
        }
        for(int d=0;d<a.length-1;d++){
            for(int e=d+1; e <a.length;e++){
                if(a[d] > a[e]){
                    numberOfInversions++;
                }
            }
        }
        return numberOfInversions;
    }
    static void test(){
        Random random = new Random();
        int num = 1000;int []arr = new int[num]; int[] b =new int[num];
        for (int c=0; c < 100000; c++){
            for(int a=0; a<num;a++){
                arr[a] = random.nextInt(1000)+100000;
            }
            if(naive(arr) != getNumberOfInversions(arr,b,0,arr.length-1)){
                System.out.println(Arrays.toString(arr));
                System.out.println(naive(arr) + " : " + getNumberOfInversions(arr,b,0,arr.length-1));
                return;
            }
        }
        System.out.print("Test complete");
    }
}
