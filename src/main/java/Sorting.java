import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Sorting {
    Random random = new Random();
    private static int partitionTwo(int []arr, int l, int r){
        int x = arr[l];
        int j = l;
        for(int i=l+1; i<=r;i++){
            if(arr[i] <= x){
                j++;
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        int temp = arr[l];
        arr[l] = arr[j];
        arr[j] = temp;
        return j;
    }
    private static int partitionThree(int [] arr,int l,int r){
        int i = l, j = r;
        if(r - l <= 1){
            if(arr[r] < arr[l]){
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
                return l;
            }
        }
        int pivot = arr[r];
        int mid = l;
        while(mid <= r){
            if (arr[mid] < pivot){
                int temp = arr[l];
                arr[l] = arr[mid];
                arr[mid] = temp;
                mid++;l++;
            }else if(arr[mid] == pivot){
                mid++;
            }else if(arr[mid] > pivot){
                int temp = arr[mid];
                arr[mid] = arr[r];
                arr[r] = temp;
                r--;
            }
        }
        i = l-1;
        j = mid;
        return l;
    }
    static void randomizedQuickSort(int[] arr, int l,int r){
        Random random = new Random();
        if ( l >= r){
            return;
        }
        int k = random.nextInt(r -l+1) +l;
        int t = arr[l];
        arr[l] = arr[k];
        arr[k] = t;

        int m = partitionThree(arr,l,r);
        randomizedQuickSort(arr,l,m-1);
        randomizedQuickSort(arr,m+1,r);
    }
    public static void main(String[] args) {
       FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[]arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scanner.nextInt();
        }
        randomizedQuickSort(arr,0,n-1);
        for(int i=0; i <n; i++){
            System.out.print(arr[i] + " ");
        }
    }
    static void test(){
        int []arr = new int[100];
        int n = arr.length;
        Random random = new Random();
        for(int a=0;a<arr.length;a++){
            arr[a] = random.nextInt(100);
        }
        randomizedQuickSort(arr,0,n-1);
        for(int i=0; i <n; i++){
            System.out.print(arr[i] + " ");
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
