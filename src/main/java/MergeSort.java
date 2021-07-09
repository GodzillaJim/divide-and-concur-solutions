import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public static int[] mergeSort(int arr[]){
        return new int[]{0};
    }
    public static void merge(int arr[], int l, int m, int r){
        int n1 = m-l+1;
        int n2 = r-m;

        int left[] = new int[n1];
        int right[]= new int[n2];

        for(int i=0;i<n1;i++){
            left[i] = arr[l+i];
        }
        for(int j=0;j<n2;j++){
            right[j] = arr[m+1+j];
        }
        int i=0,j=0;
        int k = l;
        while (i<n1 && j<n2){
            if(left[i] < right[j]){
                arr[k] = left[i];
                i++;
            }else{
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while(i < n1){
            arr[k] = left[i];
            i++;k++;
        }
        while(j < n2){
            arr[k]  = right[j];
            j++;k++;
        }
    }
    public static void sort(int arr[], int left, int right){
        if(left < right){
            int mid = (left+right)/2;
            sort(arr,left,mid);
            sort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }
    public static void main(String[] args) {
        Random random = new Random();
        int num = 10;
        int test[] = new int[num];
        for(int i=0;i<test.length;i++){
            test[i] = random.nextInt(20);
        }
        for(int d: test){
            System.out.print(d +", ");
        }
        System.out.println();
        sort(test,0,test.length-1);
        System.out.print(Arrays.toString(test));
    }
}
