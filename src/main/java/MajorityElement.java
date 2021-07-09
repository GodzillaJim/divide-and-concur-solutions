import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class MajorityElement {
    private static  int getMajorityElement(int []a, int left,int right){
       if(a.length == 0){
           return -1;
       }
        Arrays.sort(a);
       int count = 1;
       int x = a[0];
       for(int i=1; i < a.length; i++){
           if( x == a[i]){
               count++;
               if(count > a.length/2){
                   return a[i];
               }
           }else{
               x = a[i];
               count = 1;
           }
       }
        //my code
        return -1;
    }
    static void test(){
        Random random = new Random();
        int [] a = new int[100];
        for(int b=0; b<a.length;b++){
            a[b] = random.nextInt(100);
        }
        Arrays.sort(a);
        for(int c : a){
            System.out.print(c +", ");
        }
        System.out.println();
        System.out.println(getMajorityElement(a,0,a.length));
    }
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int [] a = new int[n];
        for(int i=0;i<n;i++){
            a[i] = scanner.nextInt();
        }
        if(getMajorityElement(a,0,a.length) != -1){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
    static class FastScanner{
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
