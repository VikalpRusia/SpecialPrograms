import java.util.Scanner;
// https://www.hackerrank.com/challenges/ctci-merge-sort/problem?h_r=profile

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        MergeSort mergeSort=new MergeSort(arr);
        mergeSort.main();
        return mergeSort.getCount();

    }

    public static void main(String[] args) {

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            System.out.println(result);

        }


        scanner.close();
    }
    public static class MergeSort {
        private final int[] array;
        private  final int[] temp;
        private  int count=0;

        public MergeSort(int[] y) {
            array =y;
            temp=new int[array.length];
        }

        public int getCount() {
            return count;
        }

        public  void main() {
//            array = new int[]{1,1,1,2,2};
//            temp=new int[array.length];
//        long startingTime=LocalTime.now().toNanoOfDay();
            mergeSort(0, array.length-1);
//            for (int y: array) {
//                System.out.println(y);
//            }
//        System.out.println(LocalTime.now().toNanoOfDay()-startingTime);
        }
        public  void mergeSort(int left, int right){
            if (left >= right){
                return;
            }
            int middle=(left+right)/2;
            mergeSort(left,middle);
            mergeSort(middle+1,right);
            join(left,middle,right);
//            System.out.println(count);

        }
        public  void join(int left,int middle,int right){
            int index=left;
            int leftStart=left;
            int rightStart=middle+1;
            while(leftStart<=middle && rightStart<=right){
                if (array[leftStart]<=array[rightStart]){
                    temp[index]=array[leftStart];
                    count+= Math.max((leftStart - index), 0);
                    leftStart++;
                }
                else{
                    temp[index]=array[rightStart];
                    count+=Math.max((rightStart - index), 0);;
                    rightStart++;
                }
                index++;
            }
            System.arraycopy(array,leftStart,temp,index,middle-leftStart+1);
            System.arraycopy(array,rightStart,temp,index,right-rightStart+1);
            System.arraycopy(temp,left,array,left,right-left+1);

        }
    }


}
