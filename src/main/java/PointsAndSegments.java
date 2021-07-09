import java.util.*;

public class PointsAndSegments {
    private static int [] naiveCountSegments(int[] starts, int []ends,int[] points){
        int [] counts = new int[points.length];
        for(int i=0; i< points.length; i++){
            for(int j=0; j<starts.length;j++){
                if(starts[j] <= points[i] && points[i] <= ends[j]){
                    counts[i]++;
                }
            }
        }
        return counts;
    }
    public static void main(String []args){
        Scanner scanner = new Scanner(System.in);
        int numberOfSegments, numberOfPoints;
        numberOfSegments = scanner.nextInt();
        numberOfPoints = scanner.nextInt();

        int [] starts = new int[numberOfSegments];
        int [] ends = new int[numberOfSegments];
        int [] points = new int[numberOfPoints];

        for(int i=0; i < numberOfSegments; i++ ){
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for(int i=0; i <numberOfPoints; i++){
            points[i] = scanner.nextInt();
        }
        int [] counts = fastCountSegments(starts,ends,points);
        for(int x: counts){
            System.out.print(x +" ");
        }
    }
    static void test(){
        Random random = new Random();
        int segments = 15;
        int[] starts = new int[segments];
        int [] ends = new int[segments];
        int off = 0;
        for(int t=0; t <1000; t++){
            for (int a=0; a<segments;a++){
                starts[a] = random.nextInt(200)-100;
                ends[a] = random.nextInt(200)+100;
            }
            int [] points = new int[5];
            for(int a=0;a<5;a++){
                points[a] = random.nextInt(300)-100;
            }
            if(!Arrays.equals(fastCountSegments(starts,ends,points),naiveCountSegments(starts,ends,points))){
                off++;
            }
        }
        System.out.println(off);
    }
    private static int [] fastCountSegments(final int[] starts, final int[] ends, final int[] points) {
        final int[] count = new int[points.length];
        final Pair[] pairs = new Pair[starts.length * 2 + points.length];
        final HashMap<Integer, ArrayList<Integer>> pointsMap = new HashMap<>();
        int i = 0;
        for (final int temp : starts) {
            pairs[i++] = new Pair(temp, 1);
        }
        for (final int temp : ends) {
            pairs[i++] = new Pair(temp, 3);
        }
        for (int j = 0; j < points.length; j++) {
            final int point = points[j];
            pairs[i++] = new Pair(point, 2);
            if (pointsMap.containsKey(point)) {
                pointsMap.get(point).add(j);
            } else {
                final ArrayList<Integer> list = new ArrayList<>();
                list.add(j);
                pointsMap.put(point, list);
            }
        }
        Arrays.sort(pairs, new Comparator<Pair>() {
            public int compare(final Pair pair1, final Pair pair2) {
                if (pair1.start == pair2.start) {
                    return Integer.compare(pair1.end, pair2.end);
                }
                return Integer.compare(pair1.start, pair2.start);
            }
        });
        int coverage = 0;
        for (final Pair pair : pairs) {
            if (pair.end == 1) {
                coverage++;
            } else if (pair.end == 3) {
                coverage--;
            } else {
                final ArrayList<Integer> indexes = pointsMap.get(pair.start);
                for (final Integer k : indexes) {
                    count[k] = coverage;
                }
            }
        }
        return count;
    }
    private static class Pair {
        int start, end;

        Pair(final int a, final int b) {
            this.start = a;
            this.end = b;
        }
    }
}
