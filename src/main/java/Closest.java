import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Closest {
    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for(int i = 0; i < n; i ++){
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x,y));
        writer.close();
    }
    static Double minimalDistance(int[]x, int[]y){
        //starting here
        Point[] points = new Point[y.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = Closest.getPoint(x[i],y[i]);
        }
        return findSmallestDist(points,0,points.length - 1);
    }
    static int nextInt(){
        return Integer.parseInt(next());
    }
    static String next(){
        while(!tok.hasMoreTokens()){
            String w = null;
            try{
                w = reader.readLine();
            }catch(Exception exception){
                exception.printStackTrace();
            }
            if(w == null){
                return null;
            }
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }
    public static class Point implements Comparable<Point>{
        double x, y;
        Point(final double x, final double y){
            this.x =x;
            this.y = y;
        }
        @Override
        public int compareTo(final Point point) {
            // TODO Auto-generated method stub
            return (int)(this.x - point.x);
        }
        public int compareToY(final Point point){
            return (int)(this.y - point.y);
        }
        public double calculateDistance(final Point point){
            return Math.sqrt(Math.pow((this.x - point.x), 2)+Math.pow((this.y - point.y), 2));
        }
    }
    static double naiveClosestPoints(final Point [] points){
        double min = Double.MAX_VALUE;
        for(int i =0; i <points.length;i++) {
            for(int j = i+1; j < points.length;j++){
                if(points[i].calculateDistance(points[j]) < min){
                    min = points[i].calculateDistance(points[j]);
                }
            }
        }
        return min;
    }
    static double getMinimum(final double x, final double y){
        return (x < y)? x : y;
    }
    //Handling the strip[]
    static double stripHandler(final Point[] strip, final double minDist){
        double minimumDist = minDist;
        //Sorting strip by ys
        Arrays.sort(strip, new Comparator<Point>() {
            @Override
            public int compare(final Point point1,final Point point2){
                return (int)(point1.y - point2.y);
            }
        });
        for(int d = 0; d< strip.length; d++){
            for(int e = d+1; e < strip.length && (strip[e].y - strip[d].y) < minimumDist;e++){
                if(strip[d].calculateDistance(strip[e]) < minimumDist){
                    minimumDist = strip[d].calculateDistance(strip[e]);
                }
            }
        }
        return minimumDist;
    }
    public static double findSmallestDist(final Point [] points, final int low, final int high){
        if((high -low) <= 3){
            return naiveClosestPoints(points);
        }
        //Find mid
        final int mid = low + (high - low)/2;
        final Point midPoint = points[mid];
        //Calculate d on left and right
        final double distanceLeft = findSmallestDist(points,low,mid);
        final double distanceRight = findSmallestDist(points, mid+1, high);

        //The least of the two
        final double smallestDistance = getMinimum(distanceLeft, distanceRight);
        //Building strip
        final ArrayList<Point> strip = new ArrayList<>();
        final int j = 0;
        for(int i =0; i < points.length; i++){
            if(Math.abs(points[i].x - midPoint.x) < smallestDistance){
                strip.add(points[i]);
            }
        }
        final Point[] temp = new Point[strip.size()];
        final Point[] stripp = strip.toArray(temp);
        return getMinimum(smallestDistance, stripHandler(stripp,smallestDistance));
    }
    public static Point getPoint(double x, double y){
        return new Point(x,y);
    }
    public static void test(){
        Random random = new Random();
        int m = 100;
        Point [] points = new Point[m];

        for(int b = 0; b < 10000; b++){
            for(int a=0;a<m;a++){
                points[a] = Closest.getPoint(random.nextDouble(),random.nextDouble());
            }

            if(naiveClosestPoints(points) != findSmallestDist(points,0,points.length - 1)){
                System.out.println(b + " : " + naiveClosestPoints(points) + " : " + findSmallestDist(points,0,points.length));
                return;
            }
            System.out.println(b + " : " + findSmallestDist(points,0,points.length));
        }
        System.out.println("Test Done");
    }
}
