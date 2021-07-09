import java.util.Scanner;

class MultiplyingPolynomials{
    public static int [] calculatePolyNaive(int [] A, int [] B){
        int [] product = new int [(A.length + B.length)-1];
        for(int b=0;b<product.length;b++){
            product[b] = 0;
        }
        for(int b=0;b<A.length;b++){
            for(int c =0;c<B.length;c++){
                product[b+c] = product[b+c] + A[b] * B[c];
            }
        }
        return product;
    }
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        //Entering Equation 1
        int n = scanner.nextInt();
        int [] A = new int [n+1];
        for(int a=0;a<A.length;a++){
            A[a] = scanner.nextInt(); 
        }
        //Entering second equation
        int m = scanner.nextInt();
        int [] B = new int [m+1];
        for(int a=0;a<B.length;a++){
            B[a] = scanner.nextInt();
        }
        int [] result = calculatePolyNaive(A, B);
        for (int i : result) {
            System.out.print(i + ", ");
        }
        scanner.close();
    }
    public static int [] divideAndConcurNaive(int A[],int B[],int min,int max){
        int n = A.length + 1;
        int [] result = new int[(n*2)-1];
        if(n == 1){
            result[0] = A[0]*B[0];
            return result;
        }
        return null;
    }
}