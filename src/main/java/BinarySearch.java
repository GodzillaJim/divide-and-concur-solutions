import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

class BinarySearch{
    public static int binarySearch(int []A,int low,int high, int key){
        if(low <= high){
            int mid =low + (high -low)/2;
            if(key < A[mid]){
                return binarySearch(A,low,mid-1,key);
            }
            if(key == A[mid]){
                return mid;
            }
            if(key > A[mid]){
                return binarySearch(A,mid+1,high,key);
            }
        }
        return -1;
    }
    static int linearSearch(int [] a, int x){
        for(int i=0; i < a.length; i++){
            if(a[i] == x) return i;
        }
        return -1;
    }
    public static void main(String[] args){
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int []a = new int[n];
        for(int i=0; i<n;i++){
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }
        for (int x: b) {
            System.out.print(binarySearch(a,0,a.length-1,x) + " ");
        }
    }
    static class FastScanner{
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream){
            try{
                br = new BufferedReader(new InputStreamReader(stream));
            }catch(Exception exception){
                exception.printStackTrace();
            }
        }
        String next(){
            while(st == null || !st.hasMoreTokens()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException exception){
                    exception.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
    static void test(){
        Random random = new Random();
        int []a = new int[10];
        int []b = new int [10];
        for(int i=0;i<10;i++) a[i] = random.nextInt(100);
        for(int i=0;i<10;i++) b[i] = random.nextInt(10);

        Arrays.sort(a);
        for(int n=0;n<a.length;n++){
            System.out.print(a[n] + ", ");
        }
        System.out.println();
        for(int i=0;i<b.length;i++){
            if(binarySearch(a,0,a.length-1,b[i]) != linearSearch(a,b[i])){
                System.out.println(b[i] + " : " + binarySearch(a,0,a.length-1,b[i]) + " : " + linearSearch(a,b[i]));
                return;
            }
        }
        System.out.println("All good");
    }
}