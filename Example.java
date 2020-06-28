// Numbers of length N and value less than K
// https://www.interviewbit.com/problems/numbers-of-length-n-and-value-less-than-k/#
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Example {
    static boolean speedingLoop=false;
    static int count = 0;
    static int maxLength = 0;
    static String[] inputString = null;
    static int lessThan = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            inputString = br.readLine().split(" ");
            maxLength = Integer.parseInt(br.readLine());
            lessThan = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot proceed further");
        }
        int zeroPresent = 0;
        for (String s: inputString) {
            if (Integer.parseInt(s)==0)
                zeroPresent++;

        }
        for (int i = zeroPresent; i < inputString.length; i++) {
            length(inputString[i], 1);
        }
        if (zeroPresent!=0 && maxLength == 1 && lessThan > 0) {
            count+=zeroPresent;
        }
        System.out.println(count);

    }

    private static void length(String s, int currentLength) {
        if (currentLength == maxLength) {
            if (Integer.parseInt(s) < lessThan) {
//                System.out.println(s);
                count++;
            }
            else {
                speedingLoop=true;
            }
            return;
        }
        for (String value : inputString) {
            length(s + value, currentLength + 1);
            if (speedingLoop){
                speedingLoop=false;
                return;
            }
        }
    }
}
