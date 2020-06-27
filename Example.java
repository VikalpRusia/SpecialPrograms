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
        boolean zeroPresent = inputString[0].equals("0");
        for (int i = (zeroPresent ? 1 : 0); i < inputString.length; i++) {
            length(inputString[i], 1);
        }
        if (zeroPresent && maxLength == 1 && lessThan > 0) {
            count++;
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